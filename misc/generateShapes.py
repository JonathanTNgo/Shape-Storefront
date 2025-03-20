
from PIL import Image, ImageDraw

sizes = [200, 800]

colors = {
    "red": (255, 0, 0),
    "green": (0, 255, 0),
    "blue": (0, 0, 255),
    "yellow": (255, 255, 0),
}



def draw_shape():
    for size in sizes:
        size_name = "small" if size == 200 else "large"
        for color in colors:
            color_name = "red" if color == "red" else "green" if color == "green" else "blue" if color == "blue" else "yellow"
            
            img = Image.new("RGB", (size, size), (255, 255, 255))
            draw = ImageDraw.Draw(img)

            shape_size = size // 2 
            left = (size - shape_size) // 2
            top = (size - shape_size) // 2
            right = left + shape_size
            bottom = top + shape_size
            
            draw.ellipse((left, top, right, bottom), fill=color)
            filename = "circle_{0}_{1}.png".format(color_name, size_name)
            img.save(filename)

            draw.rectangle((left, top, right, bottom), fill=color)
            filename = "square_{0}_{1}.png".format(color_name, size_name)
            img.save(filename)

            points = [(size // 2, top), (left, bottom), (right, bottom)]
            draw.polygon(points, fill=color)
            filename = "triangle_{0}_{1}.png".format(color_name, size_name)
            img.save(filename)

draw_shape()

