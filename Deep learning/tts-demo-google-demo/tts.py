from gtts import gTTS

text = input("Input the text:")

language = "vi"

tts = gTTS(text=text, lang = language, slow = False)

output_file = "output.mp3"
tts.save(output_file)

print(f"Text has been saved to {output_file}")