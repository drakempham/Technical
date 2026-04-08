import pyttsx3

engine = pyttsx3.init() # object creation

engine.setProperty("rate", 125)
engine.setProperty("volume", 1.0)

text = input("Nhap text")

engine.save_to_file(text, "output.mp3")
engine.runAndWait()

print("✅ Đã lưu file: output.mp3")