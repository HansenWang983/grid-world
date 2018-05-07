import java.awt.Image;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.imageio.ImageIO; 
import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO{
      private final static int BYTE_HEAD=14;//位图头
      private final static int BYTE_INFO=40;//位图信息
      private static final int MULTICOLOR = 24;
      private static final int GREY = 8;

      private int bitCounts;//每个像素位数

      public Image myRead(String content)throws IOException{
            FileInputStream file=new FileInputStream(content);//文件流读写
            byte[] head=new byte[BYTE_HEAD];//位图头字节缓冲区
            byte[] info=new byte[BYTE_INFO];//位图信息字节缓冲区

            //保存位图
            Image image=null;
            //保存RGB信息的字节数组
            byte[] RGB;
            //保存RGB值的数组
            int[] colorInfo;

            //分别保存位图数据大小，位图数据偏移量，宽度，高度，空字节数,总像素字节数
            int imageSize,offset,width,height,nbytes,pixelBytes;

            try{
                  file.read(head,0,BYTE_HEAD);
                  file.read(info,0,BYTE_INFO);
                  //按顺序读取           
                  //偏移量
                  offset = Bytes2Int(head,13,4);

                  //宽度
                  width = Bytes2Int(info,7,4);

                  //高度
                  height = Bytes2Int(info,11,4);

                  //像素位数
                  bitCounts = Bytes2Int(info,15,2); 
                  //位图数据大小
                  imageSize = Bytes2Int(info,23,4); 

             //计算空字节
            nbytes = (imageSize/height) -width*3;
            if(nbytes == 4){
                  nbytes = 0;
            }

            //计算总像素字节数并保存到RGB字节数组
            pixelBytes = (width+nbytes)*height*3;

            if(nbytes!=0){
                  RGB = new byte[pixelBytes];
            }
            else{
                  RGB = new byte[imageSize];
            }

            //读取RGB信息
            file.read(RGB,0,pixelBytes);
            colorInfo = new int[width*height];
            //采用255色，GREY*3
            if(bitCounts == MULTICOLOR){
                  int index = 0;
                  for(int i=0;i<height;i++){
                        for(int j=0;j<width;j++){
                              colorInfo[width*(height-i-1)+j]=
                              (255 & 0xff) << 24 | Bytes2Int(RGB, index + 2, 3); 
                              index+=3;
                        }
                        index+=nbytes;
                  }

                  image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width,height,colorInfo,0,width));
            }     

            //灰度图
            if(bitCounts == GREY){
                  int index = offset;
                  for(int i=0;i<height;i++){
                        for(int j=0;j<width;j++){
                              if(index>=pixelBytes){
                                    index=0;
                              }
                              int temp = ((int)RGB[index] & 0xff);
                              colorInfo[width*(height-i-1)+j]=
                              (255 & 0xff) << 24 | temp << 16 | temp << 8 | temp;
                        index+=1;
                        }
                        index+=nbytes;
                  }

                  image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width,height,colorInfo,0,width));
            }
            file.close();
            }catch(Exception e){
                  e.getMessage();
            }
            return image;
      }

      public Image myWrite(Image image,String content)throws IOException{
            try{
                  int imageWidth=image.getWidth(null);
                  int imageHeight=image.getHeight(null);
                  int fileType;

                  //位图类型
                  if(bitCounts==MULTICOLOR){
                        fileType = BufferedImage.TYPE_3BYTE_BGR;
                  }
                  else{
                        fileType = BufferedImage.TYPE_BYTE_GRAY;
                  }
                  //创建图片
                  BufferedImage buffimage=new BufferedImage(imageWidth,imageHeight,fileType);
                  Graphics2D bGr = buffimage.createGraphics();
                  bGr.drawImage(image, 0, 0, null);
                  bGr.dispose();

                  //新建bmp文件
                  File _file=new File(content+".bmp");
                  //保存图片
                  ImageIO.write(buffimage,"bmp",_file);

            }catch(Exception e){
                  e.getMessage();
            }
            return image;
      }

      private int Bytes2Int(byte[] arr, int start, int length) {
        int temp = 0;
        for (int i = length - 1; i >= 0; --i) {
            temp |= (int) (arr[start-i] & 0xff) << (8 * (length - i - 1));
        }
        return temp;
    }
}