from dotenv import load_dotenv
from supabase import create_client
import os

load_dotenv()

# Grab .env keys
url = os.environ.get("SUPABASE_URL")
key = os.environ.get("SUPABASE_KEY")

supabase = create_client(url, key)


# Database schema
# ID, type (shapes), color (Color), name (text), imageUrl_small (text), imageUrl_large (text)

# EXAMPLE
# 1
# circle
# red
# circle_red_small
# https://yreiokyimjpronjajfjr.supabase.co/storage/v1/object/public/shape-images//circle_red_small.png
# https://yreiokyimjpronjajfjr.supabase.co/storage/v1/object/public/shape-images//circle_red_large.png

def populate_database():
    id = 1

    # Construct the path to the "photos" folder (located in misc/)
    images_folder = os.path.join(os.path.dirname(os.path.abspath(__file__)), "../photos")
    for file in os.listdir(images_folder):
        # Since every file has a small and large version, we can just check for the small version
        if file.endswith("small.png"):
            # Get the file name without the extension
            file_name = file.split(".")[0]
            name_temp = file_name.split("_")
            shape_type = name_temp[0]
            shape_color = name_temp[1]
            
            shape_id = 1
            
            try:
                response = supabase.table("Products").insert({
                    "id": id,
                    "type": shape_type.upper(),
                    "color": shape_color.upper(),
                    "name": file_name,
                    "imageUrl_small": f"https://yreiokyimjpronjajfjr.supabase.co/storage/v1/object/public/shape-images//{shape_type}_{shape_color}_small.png",
                    "imageUrl_large": f"https://yreiokyimjpronjajfjr.supabase.co/storage/v1/object/public/shape-images//{shape_type}_{shape_color}_large.png"
                }).execute();
                id += 1
                
                print(response)
            except Exception as e:
                print(e)


populate_database()
