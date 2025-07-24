private String buildPrompt(String prompt) {
    return "You are an AI assistant helping students create study schedules.\n"
            + "IMPORTANT: Please create ONLY ONE schedule at a time.\n"
            + "IMPORTANT: All fields in the response MUST start with an asterisk (*)\n"
            + "\n### Study Schedule Format ###\n"
            + "*Tiêu đề: [What to study]\n"
            + "*Nội dung: [Details of the study session]\n"
            + "*Ghi chú: [Additional information]\n"
            + "*Thời gian bắt đầu: [YYYY-MM-DD HH:mm]\n"
            + "*Thời gian kết thúc: [YYYY-MM-DD HH:mm]\n\n"
            + "Example response format:\n"
            + "*Tiêu đề: Math Study Session\n"
            + "*Nội dung: Review algebra\n"
            + "*Ghi chú: Bring calculator\n"
            + "*Thời gian bắt đầu: 2024-03-31 14:00\n"
            + "*Thời gian kết thúc: 2024-03-31 16:00\n\n"
            + "Please create ONE study schedule for: " + prompt;
}

private void sendMessageToAI() {
    String userMessage = inputField.getText().trim();

    if (userMessage.isEmpty()) {
        return;
    }
    appendMessage("User", userMessage, defaultUserAvatar);
    inputField.setText("");
    
    if (userMessage.toLowerCase().contains("ngày trong tương lai")) {
        insertFutureDaysToDatabase(10);
        return;
    }
    if (!isValidTaskPrompt(userMessage)) {
        appendMessage("Bot", "⚠️ Yêu cầu không hợp lệ. Hãy yêu cầu tôi tạo nhiệm vụ!", avatarPath);
        return;
    }
    
    // Gửi tin nhắn đến AI
    new Thread(() -> {
        String aiResponse = getAIResponse(userMessage);
        if (aiResponse != null && !aiResponse.startsWith("[Error")) {
            // Hiển thị phản hồi cho người dùng
            SwingUtilities.invokeLater(() -> {
                appendMessage("Bot", aiResponse, avatarPath);
                // Sau khi hiển thị, thực hiện insert
                insertSchedule(userMessage, aiResponse);
            });
        } else {
            // Hiển thị thông báo lỗi
            SwingUtilities.invokeLater(() -> 
                appendMessage("Bot", "⚠️ Xin lỗi, tôi không thể tạo lịch học hợp lệ. Vui lòng thử lại!", avatarPath)
            );
        }
    }).start();
}

private String getAIResponse(String prompt) {
    int retryCount = 0;

    while (retryCount < MAX_RETRIES) {
        try {
            JSONObject requestJson = new JSONObject()
                    .put("model", MODEL)
                    .put("prompt", buildPrompt(prompt))
                    .put("stream", false)
                    .put("max_tokens", 400);

            RequestBody body = RequestBody.create(requestJson.toString(), JSON);
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful() || response.body() == null) {
                    throw new IOException("\u274C API Error: " + response.code());
                }

                String responseBody = response.body().string();
                JSONObject responseJson = new JSONObject(responseBody);
                String aiResponse = responseJson.optString("response", "").trim();

                if (!isValidResponse(aiResponse)) {
                    logger.log(Level.WARNING, "⚠️ AI response format is incorrect: " + aiResponse);
                    return "[Error: AI response is missing required fields]";
                }

                return aiResponse;
            }
        } catch (Exception e) {
            retryCount++;
            logger.log(Level.WARNING, "⛔ API error, retrying " + retryCount + "/" + MAX_RETRIES, e);

            if (retryCount < MAX_RETRIES) {
                try {
                    Thread.sleep(1000L * retryCount);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    return "[Error: Interrupted while retrying]";
                }
            }
        }
    }
    return "[Error: Failed after " + MAX_RETRIES + " attempts]";
}

private boolean isValidResponse(String response) {
    // Đếm số lần xuất hiện của "Tiêu đề:" hoặc "*Tiêu đề:"
    long titleCount = response.lines()
            .filter(line -> line.contains("Tiêu đề:") || line.contains("*Tiêu đề:"))
            .count();
            
    // Nếu có nhiều hơn 1 tiêu đề, coi như không hợp lệ
    if (titleCount > 1) {
        return false;
    }

    // Kiểm tra các trường bắt buộc
    return (response.contains("Tiêu đề:") || response.contains("*Tiêu đề:")) &&
           (response.contains("Nội dung:") || response.contains("*Nội dung:")) &&
           (response.contains("Ghi chú:") || response.contains("*Ghi chú:")) &&
           (response.contains("Thời gian bắt đầu:") || response.contains("*Thời gian bắt đầu:")) &&
           (response.contains("Thời gian kết thúc:") || response.contains("*Thời gian kết thúc:"));
}

private List<Map<String, String>> extractMultipleSchedules(String response) {
    List<Map<String, String>> schedules = new ArrayList<>();
    String[] blocks = response.split("\n\n");
    
    for (String block : blocks) {
        if (block.contains("Tiêu đề:") || block.contains("*Tiêu đề:")) {
            Map<String, String> schedule = new HashMap<>();
            schedule.put("tieuDe", extractField(block, "Tiêu đề:"));
            schedule.put("noiDung", extractField(block, "Nội dung:"));
            schedule.put("ghiChu", extractField(block, "Ghi chú:"));
            schedule.put("thoiGianBatDau", extractField(block, "Thời gian bắt đầu:"));
            schedule.put("thoiGianKetThuc", extractField(block, "Thời gian kết thúc:"));
            schedules.add(schedule);
        }
    }
    
    return schedules;
} 