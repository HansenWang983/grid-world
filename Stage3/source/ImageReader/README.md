# ImageReader

## 图像处理部分

### 1.利用二进制流读取文件，设置字节缓冲区将文件流读取进去，然后转换成相应的整型数值。

- colorInfo为每个像素的RGB值数组（int）

- RGB为存储RGB值的字节数组

- 关键函数

  ```Java
  private int Bytes2Int(byte[] arr, int start, int length);
  ```



### 2.把读取的彩色图像转换灰度图像

- 利用公式I = 0.299 * R + 0.587 * G + 0.114 *B将提取到的RGB值计算得到结果


- 关键函数

  ```java
  Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(),gray));
  ```

  其中gray为继承RGBImageFilter类的一个对象，其public int filterRGB(int x, int y, int rgb)v成员函数负责将初始RGB值按指定方法过滤成所需的灰度值



### 3.显示各个颜色通道

- 同理灰度过滤的方法，重写public int filterRGB(int x, int y, int rgb)将传进来rgb值按位操作方法生成新RGB值。



### 4.保存成bmp格式

- 判断为灰度图还是彩色图，确定每一个像素的位数


- 关键函数

```java
BufferedImage buffimage=new BufferedImage(imageWidth,imageHeight,fileType);
Graphics2D bGr = buffimage.createGraphics();
bGr.drawImage(image, 0, 0, null);
bGr.dispose();

//新建bmp文件
File _file=new File(content+".bmp");
//保存图片
ImageIO.write(buffimage,"bmp",_file);
```

将图片导入buffimage，并写入文件，格式为bmp。



### 5.JUnit

- 读取初始图片和目标图片，将初始图片执行showChanelB等处理函数得到的函数与目标图片进行比较。

比较内容：

1. 每个像素的RGB值，可使用BufferedImage的getRGB函数
2. 两张图片的长和宽度。

- 对每一个处理函数执行两次处理，总共8个测试。



### 6.Sonar

1. 在读入灰度图或者彩色图中只有每个像素位数的不同，取消了大部分重复的代码。
2. 实现Bytes2Int函数，避免对于存储字长为4字节或2字节的数据的每8位的位操作。









