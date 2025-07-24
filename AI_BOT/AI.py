from openai import OpenAI

client = OpenAI(
    base_url="http://localhost:11434/v1",
    api_key="ollama"
)


messages = []

while True:

    user_input = input("\nYou: ") #đọc từ mic
    if user_input.lower() in ["exit", "bye", "tạm biệt"]:
        break

    messages.append({"role": "user", "content": user_input})

    response = client.chat.completions.create(
        model="gemma2:9b",
        stream=True,
        messages=messages
    )
    
    bot_reply = ""  
    print("\nBot: ", end="")
    for chunk in response:
        bot_reply += chunk.choices[0].delta.content or ""
        print(chunk.choices[0].delta.content or "", end="", flush=True)
        
    # tích hợp text-to-speech để phát lại câu hỏi
    messages.append({"role": "assistant", "content": bot_reply})