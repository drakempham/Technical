import wave
from pathlib import Path
from piper.voice import PiperVoice

# 3 Vietnamese models available — switch by changing MODEL_PATH:
#   vi_VN-vais1000-medium.onnx      63 MB  best quality (1000h dataset)
#   vi_VN-25hours_single-low.onnx   ~20 MB lower quality
#   vi_VN-vivos-x_low.onnx          smallest, fastest
MODEL_PATH = "models/vi_VN-25hours_single-low.onnx"
OUTPUT_FILE = "output.wav"


def synthesize(text: str, output_path: str = OUTPUT_FILE) -> None:
    if not Path(MODEL_PATH).exists():
        raise FileNotFoundError(
            f"\nModel not found: {MODEL_PATH}\n"
            "Tải model bằng lệnh:\n"
            "  curl -L -o models/vi_VN-vais1000-medium.onnx \\\n"
            "    'https://huggingface.co/rhasspy/piper-voices/resolve/main/"
            "vi/vi_VN/vais1000/medium/vi_VN-vais1000-medium.onnx'\n"
            "  curl -L -o models/vi_VN-vais1000-medium.onnx.json \\\n"
            "    'https://huggingface.co/rhasspy/piper-voices/resolve/main/"
            "vi/vi_VN/vais1000/medium/vi_VN-vais1000-medium.onnx.json'"
        )

    voice = PiperVoice.load(MODEL_PATH)

    with wave.open(output_path, "wb") as wav_file:
        wav_file.setnchannels(1)
        wav_file.setsampwidth(2)
        wav_file.setframerate(voice.config.sample_rate)
        voice.synthesize(text, wav_file)

    size_kb = Path(output_path).stat().st_size // 1024
    print(f"Saved: {output_path}  ({size_kb} KB)")
    print(f"Play:  afplay {output_path}")


if __name__ == "__main__":
    text = input("Nhập câu cần đọc: ").strip()
    if not text:
        text = "Xin chào, đây là bài thực hành Piper trên máy của tôi."
        print(f"Dùng câu mặc định: {text}")
    synthesize(text)
