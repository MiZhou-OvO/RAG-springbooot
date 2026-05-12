from pathlib import Path
import shutil

from docx import Document


ROOT = Path(r"D:\EGlaoding\idea.D\java")
DESKTOP = Path.home() / "Desktop"
REPORT_MD = ROOT / "实验一_实验报告_图片预留版.md"
DOCX_NAME = "实验一_Spring Boot整合mybatis-plus的搭建.docx"
DOCX_PATH = DESKTOP / DOCX_NAME
BACKUP_PATH = DESKTOP / "lab1_report_template_backup.docx"


SECTION_MAP = {
    "四、实验目的与要求": "实验目的与要求",
    "五、实验原理与内容": "实验原理与内容",
    "六、实验设备与软件环境": "实验设备与软件环境",
    "七、实验过程与结果": "实验过程与结果（可贴图）",
    "八、操作异常问题与解决方案": "操作异常问题与解决方案（重点）",
    "九、实验总结": "实验总结",
}


def parse_markdown_sections(text: str) -> dict[str, str]:
    sections: dict[str, list[str]] = {}
    current = None
    for raw_line in text.splitlines():
        line = raw_line.rstrip()
        if line.startswith("## "):
            current = line[3:].strip()
            sections[current] = []
            continue
        if current is not None:
            sections[current].append(line)

    normalized: dict[str, str] = {}
    for key, lines in sections.items():
        cleaned: list[str] = []
        previous_blank = False
        for line in lines:
            text_line = (
                line.replace("### ", "")
                .replace("#### ", "")
                .replace("`", "")
                .replace("**", "")
            )
            if text_line.startswith("- "):
                text_line = text_line[2:]
            if not text_line.strip():
                if not previous_blank:
                    cleaned.append("")
                previous_blank = True
                continue
            cleaned.append(text_line)
            previous_blank = False
        normalized[key] = "\n".join(cleaned).strip()
    return normalized


def fill_table_cell(cell, title: str, content: str) -> None:
    cell.text = f"{title}\n{content}".strip()


def main() -> None:
    if not BACKUP_PATH.exists():
        raise FileNotFoundError(f"Backup template not found: {BACKUP_PATH}")
    if not REPORT_MD.exists():
        raise FileNotFoundError(f"Report markdown not found: {REPORT_MD}")

    shutil.copyfile(BACKUP_PATH, DOCX_PATH)

    sections = parse_markdown_sections(REPORT_MD.read_text(encoding="utf-8"))
    doc = Document(str(DOCX_PATH))
    report_table = doc.tables[1]

    for row_index, (md_heading, word_title) in enumerate(SECTION_MAP.items()):
        content = sections.get(md_heading)
        if not content:
            raise ValueError(f"Missing section in markdown: {md_heading}")
        fill_table_cell(report_table.rows[row_index].cells[0], word_title, content)

    doc.save(str(DOCX_PATH))


if __name__ == "__main__":
    main()
