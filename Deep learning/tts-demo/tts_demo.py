from gtts import gTTS

# Nhận input text từ người dùng
text = input("Nhập text: ")

# Tạo đối tượng gTTS (lang='vi' cho tiếng Việt)
tts = gTTS(text=text, lang="vi", slow=False)

# Lưu ra file .mp3
tts.save("output.mp3")

print("✅ Đã lưu file: output.mp3")