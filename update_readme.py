import os
import re

def format_name(slug):
    # Converts "two-sum" into "Two Sum"
    return ' '.join(word.capitalize() for word in slug.split('-'))

def main():
    # 1. Get all folders that start with a digit (e.g., "1-two-sum")
    folders = [f for f in os.listdir('.') if os.path.isdir(f) and f[0].isdigit()]
    
    # 2. Sort folders numerically by the ID
    folders.sort(key=lambda x: int(x.split('-')[0]))

    # 3. Build the table header
    table = "| # | Problem Name | Solution Link | Difficulty |\n"
    table += "|---|---|---|---|\n"

    # 4. Generate rows
    for folder in folders:
        parts = folder.split('-', 1)
        if len(parts) == 2:
            prob_id = parts[0]
            slug = parts[1]
            prob_name = format_name(slug)
            
            # Extract difficulty from LeetSync's inner README.md if it exists
            difficulty = "Unknown"
            readme_path = os.path.join(folder, "README.md")
            if os.path.exists(readme_path):
                with open(readme_path, 'r', encoding='utf-8') as f:
                    content = f.read()
                    if "Easy" in content: difficulty = "🟢 Easy"
                    elif "Medium" in content: difficulty = "🟡 Medium"
                    elif "Hard" in content: difficulty = "🔴 Hard"

            table += f"| {prob_id} | {prob_name} | [View Solution](./{folder}/) | {difficulty} |\n"

    # 5. Inject the new table into the main README.md
    with open('README.md', 'r', encoding='utf-8') as f:
        readme_content = f.read()

    # Regex to replace everything between the start and end markers
    new_content = re.sub(
        r"(<!-- table-start -->\n).*?(\n<!-- table-end -->)",
        rf"\g<1>{table}\g<2>",
        readme_content,
        flags=re.DOTALL
    )

    with open('README.md', 'w', encoding='utf-8') as f:
        f.write(new_content)

if __name__ == "__main__":
    main()
