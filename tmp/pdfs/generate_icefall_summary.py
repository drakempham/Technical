from pathlib import Path
import textwrap


OUT = Path("/Users/minhpd/Code/Technical/output/pdf/icefall-one-page-summary.pdf")

PAGE_W, PAGE_H = 612, 792
MARGIN = 42


def esc(text):
    return text.replace("\\", "\\\\").replace("(", "\\(").replace(")", "\\)")


class PDF:
    def __init__(self):
        self.ops = []

    def raw(self, op):
        self.ops.append(op)

    def text(self, x, y, text, size=9, font="F1", color=(32, 41, 54)):
        r, g, b = [v / 255 for v in color]
        self.ops.append(f"{r:.3f} {g:.3f} {b:.3f} rg")
        self.ops.append(f"BT /{font} {size} Tf {x:.2f} {y:.2f} Td ({esc(text)}) Tj ET")

    def line(self, x1, y1, x2, y2, color=(206, 214, 224), width=0.8):
        r, g, b = [v / 255 for v in color]
        self.ops.append(f"{r:.3f} {g:.3f} {b:.3f} RG {width:.2f} w")
        self.ops.append(f"{x1:.2f} {y1:.2f} m {x2:.2f} {y2:.2f} l S")

    def rect(self, x, y, w, h, fill=(247, 249, 252), stroke=(222, 228, 236)):
        fr, fg, fb = [v / 255 for v in fill]
        sr, sg, sb = [v / 255 for v in stroke]
        self.ops.append(f"{fr:.3f} {fg:.3f} {fb:.3f} rg {sr:.3f} {sg:.3f} {sb:.3f} RG")
        self.ops.append(f"{x:.2f} {y:.2f} {w:.2f} {h:.2f} re B")

    def section(self, title, x, y, w):
        self.text(x, y, title.upper(), 8.5, "F2", (47, 89, 137))
        self.line(x, y - 5, x + w, y - 5, (198, 211, 226), 0.6)
        return y - 18

    def para(self, x, y, text, width_chars, size=8.6, leading=11.5, color=(41, 51, 65)):
        for line in textwrap.wrap(text, width_chars):
            self.text(x, y, line, size, "F1", color)
            y -= leading
        return y

    def bullets(self, x, y, items, width_chars, size=8.3, leading=10.7):
        for item in items:
            wrapped = textwrap.wrap(item, width_chars)
            self.text(x, y, "- " + wrapped[0], size)
            y -= leading
            for cont in wrapped[1:]:
                self.text(x + 8, y, cont, size)
                y -= leading
        return y

    def code_lines(self, x, y, lines, size=7.3, leading=9.3):
        for line in lines:
            self.text(x, y, line, size, "F3", (31, 43, 58))
            y -= leading
        return y

    def write(self, path):
        stream = "\n".join(self.ops).encode("latin-1")
        objs = []
        objs.append(b"<< /Type /Catalog /Pages 2 0 R >>")
        objs.append(b"<< /Type /Pages /Kids [3 0 R] /Count 1 >>")
        objs.append(
            b"<< /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] "
            b"/Resources << /Font << /F1 4 0 R /F2 5 0 R /F3 6 0 R >> >> "
            b"/Contents 7 0 R >>"
        )
        objs.append(b"<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>")
        objs.append(b"<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica-Bold >>")
        objs.append(b"<< /Type /Font /Subtype /Type1 /BaseFont /Courier >>")
        objs.append(b"<< /Length " + str(len(stream)).encode() + b" >>\nstream\n" + stream + b"\nendstream")

        pdf = bytearray(b"%PDF-1.4\n%\xe2\xe3\xcf\xd3\n")
        offsets = [0]
        for i, obj in enumerate(objs, start=1):
            offsets.append(len(pdf))
            pdf.extend(f"{i} 0 obj\n".encode())
            pdf.extend(obj)
            pdf.extend(b"\nendobj\n")
        xref = len(pdf)
        pdf.extend(f"xref\n0 {len(objs) + 1}\n".encode())
        pdf.extend(b"0000000000 65535 f \n")
        for off in offsets[1:]:
            pdf.extend(f"{off:010d} 00000 n \n".encode())
        pdf.extend(
            f"trailer\n<< /Size {len(objs) + 1} /Root 1 0 R >>\nstartxref\n{xref}\n%%EOF\n".encode()
        )
        path.write_bytes(pdf)


pdf = PDF()

pdf.raw("0.965 0.979 0.996 rg 0 744 612 48 re f")
pdf.text(MARGIN, 762, "icefall - One-Page App Summary", 18, "F2", (20, 44, 74))
pdf.text(MARGIN, 745, "Scope: /Users/minhpd/Code/Technical/icefall subrepo; content is based only on local repo evidence.", 7.8, "F1", (84, 97, 113))
pdf.line(MARGIN, 735, PAGE_W - MARGIN, 735, (174, 195, 218), 1.0)

left_x, right_x = MARGIN, 319
col_w, right_w = 250, 251
y_left, y_right = 713, 713

y_left = pdf.section("What It Is", left_x, y_left, col_w)
y_left = pdf.para(
    left_x,
    y_left,
    "icefall is a Python collection of speech-related recipes for datasets using k2-fsa and lhotse. The repo also points to sherpa, sherpa-ncnn, and sherpa-onnx for deploying models trained or used with icefall.",
    58,
)

y_left -= 7
y_left = pdf.section("Who It's For", left_x, y_left, col_w)
y_left = pdf.bullets(
    left_x,
    y_left,
    [
        "Primary persona: Not found in repo.",
        "Evidence suggests speech/ML developers and researchers working with ASR/TTS/SLU recipes, pretrained speech models, and k2/lhotse-based experiments.",
    ],
    56,
)

y_left -= 7
y_left = pdf.section("What It Does", left_x, y_left, col_w)
y_left = pdf.bullets(
    left_x,
    y_left,
    [
        "Provides dataset recipes under egs/ for ASR, TTS, SLU, ST, KWS, AT, and related speech tasks.",
        "Supports many ASR datasets, including yesno, Aishell, CommonVoice, GigaSpeech, LibriSpeech, TED-LIUM3, VoxPopuli, WenetSpeech, and more.",
        "Includes model families such as TDNN LSTM CTC, Conformer CTC/MMI, Zipformer, transducer variants, and Whisper fine-tuning for Aishell-1.",
        "Supplies shared Python utilities for decoding, checkpoints, graph compilers, lexicons, language models, distributed helpers, diagnostics, and datasets.",
        "Includes recipe scripts for data preparation, feature computation, language/HLG graph preparation, training, decoding, export, ONNX, and pretrained-model use.",
        "Documents model export paths such as ONNX, TorchScript, NCNN, and model state_dict export.",
        "Links browser-based pretrained-model demos through a Hugging Face Space.",
    ],
    57,
    size=7.65,
    leading=9.65,
)

y_right = pdf.section("How It Works", right_x, y_right, right_w)
pdf.rect(right_x - 5, y_right - 122, right_w + 8, 128)
y_box = y_right - 8
flow = [
    "Datasets + audio",
    "-> egs/<dataset>/<task>/prepare.sh",
    "-> lhotse manifests + fbank features",
    "-> language resources + k2 FSA graphs",
    "-> recipe model train.py",
    "-> checkpoints / exp artifacts",
    "-> decode.py / export.py / deployment toolchain",
]
y_box = pdf.code_lines(right_x + 5, y_box, flow, 7.5, 11)
y_right = y_right - 143
y_right = pdf.bullets(
    right_x,
    y_right,
    [
        "Core library: icefall/*.py contains reusable training/decoding support, graph compilers, lexicon helpers, LM wrappers, checkpointing, and environment utilities.",
        "Recipes: egs/ groups dataset-specific pipelines; the yesno recipe shows prepare.sh -> TDNN or transducer train/decode/export scripts.",
        "External services/components evidenced in docs: k2, lhotse, torch, torchaudio, kaldifst/kaldilm, ONNX/ONNX Runtime, tensorboard, and optional sherpa-family deployment projects.",
        "Data flow evidence: yesno prepare.sh downloads data, prepares manifests, computes fbank features, prepares language files, creates G, and compiles HLG before training/decoding.",
        "Runtime server/API: Not found in repo evidence inspected; this appears to be a recipe/toolkit repo, not a single hosted web service.",
    ],
    57,
    size=7.7,
    leading=9.7,
)

y_right -= 8
y_right = pdf.section("How To Run", right_x, y_right, right_w)
y_right = pdf.bullets(
    right_x,
    y_right,
    [
        "Install prerequisites in documented order: CUDA/cuDNN if needed, torch + torchaudio, k2, then lhotse.",
        "From the icefall repo: install Python requirements and set PYTHONPATH to the repo path.",
        "Smoke test with the CPU yesno recipe: prepare data, optionally hide GPUs, then run TDNN training and decoding.",
    ],
    57,
    size=7.8,
    leading=9.8,
)
pdf.rect(right_x - 5, y_right - 54, right_w + 8, 60, fill=(250, 251, 253), stroke=(225, 230, 237))
pdf.code_lines(
    right_x + 5,
    y_right - 10,
    [
        "cd /Users/minhpd/Code/Technical/icefall",
        "pip install -r requirements.txt",
        "export PYTHONPATH=$PWD:$PYTHONPATH",
        "cd egs/yesno/ASR && ./prepare.sh",
        "export CUDA_VISIBLE_DEVICES=\"\" && ./tdnn/train.py",
    ],
    size=6.9,
    leading=9.2,
)

pdf.line(MARGIN, 38, PAGE_W - MARGIN, 38, (211, 219, 229), 0.6)
pdf.text(MARGIN, 25, "Evidence checked: README.md, docs/source/installation/index.rst, requirements.txt, icefall/ package tree, egs/yesno/ASR scripts.", 6.8, "F1", (91, 103, 119))

pdf.write(OUT)
print(OUT)
