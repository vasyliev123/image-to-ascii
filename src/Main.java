import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

    public static String chars = "@%#*+=-:. "; //10
    public static void main(String[] args) {


        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("src/sample.jpg"));


            Character[][] result=convert(img);
            BufferedWriter output = null;
            try {
                File file = new File("Result.txt");
                output = new BufferedWriter(new FileWriter(file));

                for (int i = 0; i < img.getWidth(); i++) {
                    for (int j = 0; j < img.getHeight(); j++) {
                        output.write(result[i][j]);
                    }
                    output.write('\n');
                }
            }catch ( IOException e ) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }catch (IOException e)
        {
            System.out.print("No image "+e);
        }


    }
    private static Character[][] convert(BufferedImage image) {


        final int width = image.getWidth();
        final int height = image.getHeight();


        Character[][] result = new Character[width][height];

        for(int i =0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                int clr = image.getRGB(i, j);

                int red =   (clr>>16)&255;
                int green =   (clr>>8)&255;
                int blue =   (clr)&255;

                int avg = (red+green+blue)/3;
                int pos = (int)Math.floor(avg /25.50000001);

                result[i][j]=chars.charAt(pos);

            }
        }

        return result;
    }
}
