package opencv;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.net.URL;
import processing.core.PApplet;
import processing.core.PImage;

// Referenced classes of package hypermedia.video:
//            Blob

public class OpenCV
{

    public static final int SOURCE = 0;
    public static final int BUFFER = 1;
    public static final int MEMORY = 2;
    public static final int RGB = 1;
    public static final int GRAY = 12;
    public static final int THRESH_BINARY = 0;
    public static final int THRESH_BINARY_INV = 1;
    public static final int THRESH_TRUNC = 2;
    public static final int THRESH_TOZERO = 3;
    public static final int THRESH_TOZERO_INV = 4;
    public static final int THRESH_OTSU = 8;
    public static final int FLIP_VERTICAL = 0;
    public static final int FLIP_HORIZONTAL = 1;
    public static final int FLIP_BOTH = -1;
    public static final int INTER_NN = 0;
    public static final int INTER_LINEAR = 1;
    public static final int INTER_CUBIC = 2;
    public static final int INTER_AREA = 3;
    public static final int MAX_VERTICES = 1024;
    public static final int BLUR = 1;
    public static final int GAUSSIAN = 2;
    public static final int MEDIAN = 3;
    public static final int BILATERAL = 4;
    public static final int MOVIE_MILLISECONDS = 0;
    public static final int MOVIE_FRAMES = 1;
    public static final int MOVIE_RATIO = 2;
    public static final int MOVIE_WIDTH = 3;
    public static final int MOVIE_HEIGHT = 4;
    public static final int MOVIE_FPS = 5;
    public static final int MOVIE_FRAME_COUNT = 7;
    public static final int MOVIE_FORMAT = 8;
    public static final int MOVIE_MODE = 9;
    public static final int MOVIE_BRIGHTNESS = 10;
    public static final int MOVIE_CONTRAST = 11;
    public static final int MOVIE_SATURATION = 12;
    public static final int MOVIE_HUE = 13;
    public static final int MOVIE_GAIN = 14;
    public static final int MOVIE_CONVERT_RGB = 15;
    public static final int HAAR_DO_CANNY_PRUNING = 1;
    public static final int HAAR_SCALE_IMAGE = 2;
    public static final int HAAR_FIND_BIGGEST_OBJECT = 4;
    public static final int HAAR_DO_ROUGH_SEARCH = 8;
    public static final String CASCADE_FRONTALFACE_ALT_TREE = "haarcascade_frontalface_alt_tree.xml";
    public static final String CASCADE_FRONTALFACE_ALT = "haarcascade_frontalface_alt.xml";
    public static final String CASCADE_FRONTALFACE_ALT2 = "haarcascade_frontalface_alt2.xml";
    public static final String CASCADE_FRONTALFACE_DEFAULT = "haarcascade_frontalface_default.xml";
    public static final String CASCADE_PROFILEFACE = "haarcascade_profileface.xml";
    public static final String CASCADE_FULLBODY = "haarcascade_fullbody.xml";
    public static final String CASCADE_LOWERBODY = "haarcascade_lowerbody.xml";
    public static final String CASCADE_UPPERBODY = "haarcascade_upperbody.xml";
    protected PApplet parent;
    public int width;
    public int height;
    private int brightness;
    private int contrast;
    private static String OS;

    public OpenCV()
    {
        this(null);
    }

    public OpenCV(PApplet parent)
    {
        this.parent = null;
        width = 0;
        height = 0;
        brightness = 0;
        contrast = 0;
        this.parent = parent;
        try
        {
            if(parent instanceof PApplet)
            {
                parent.registerDispose(this);
            }
        }
        catch(NoClassDefFoundError e) { }
    }

    public void dispose()
    {
        stop();
    }

    public native void allocate(int i, int j);

    private native void deallocate(int i);

    public void stop()
    {
        deallocate(-1);
    }

    public void remember()
    {
        remember(0, -2);
    }

    public void remember(int type)
    {
        remember(type, -2);
    }

    public native void remember(int i, int j);

    public native void convert(int i);

    public native void absDiff();

    public native void flip(int i);

    public native void threshold(float f, float f1, int i);

    public void threshold(float value)
    {
        threshold(value, 255F, 0);
    }

    public void brightness(int value)
    {
        brightness = Math.min(128, Math.max(value, -128));
        brightnessContrast(brightness, contrast);
    }

    public void contrast(int value)
    {
        contrast = Math.min(128, Math.max(value, -128));
        brightnessContrast(brightness, contrast);
    }

    private native void brightnessContrast(int i, int j);

    public native void invert();

    public void blur(int type, int param1)
    {
        blur(type, param1, 0, 0.0F, 0.0F);
    }

    public native void blur(int i, int j, int k, float f, float f1);

    public native void interpolation(int i);

    public void copy(PImage image)
    {
        copy(image, 0, 0, image.width, image.height, 0, 0, image.width, image.height);
    }

    public void copy(String file)
    {
        copy(file, 0, 0, -1, -1, 0, 0, -1, -1);
    }

    public void copy(PImage image, int sx, int sy, int swidth, int sheight, int dx, int dy, 
            int dwidth, int dheight)
    {
        copy(image.pixels, image.width, sx, sy, swidth, sheight, dx, dy, dwidth, dheight);
    }

    public native void copy(String s, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1);

    public native void copy(int ai[], int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1, int i2);

    public void restore()
    {
        restore(1);
    }

    public native void restore(int i);

    public native int[] pixels(int i);

    public int[] pixels()
    {
        return pixels(1);
    }

    public void loadImage(String file)
    {
        loadImage(file, -1, -1);
    }

    public native void loadImage(String s, int i, int j);

    public PImage image()
    {
        return image(1);
    }

    public PImage image(int type)
    {
        if(width == 0 && height == 0)
        {
            System.err.println("OpenCV could not define source dimensions.\n");
            return null;
        } else
        {
            PImage img = new PImage(width, height);
            img.pixels = pixels(type);
            return img;
        }
    }

    public Blob[] blobs(int minArea, int maxArea, int maxBlobs, boolean findHoles)
    {
        return blobs(minArea, maxArea, maxBlobs, findHoles, 1024);
    }

    public native Blob[] blobs(int i, int j, int k, boolean flag, int l);

    /**
     * @deprecated Method findContours is deprecated
     */

    public void findContours(int minArea, int maxArea, int maxBlobs, boolean findHoles)
    {
        findContours(minArea, maxArea, maxBlobs, findHoles, 1024);
    }

    /**
     * @deprecated Method findContours is deprecated
     */

    public native void findContours(int i, int j, int k, boolean flag, int l);

    /**
     * @deprecated Method blobCount is deprecated
     */

    public native int blobCount();

    /**
     * @deprecated Method area is deprecated
     */

    public native float area(int i);

    /**
     * @deprecated Method arcLength is deprecated
     */

    public native float arcLength(int i);

    /**
     * @deprecated Method centroid is deprecated
     */

    public native Point centroid(int i);

    /**
     * @deprecated Method rectangle is deprecated
     */

    public native Rectangle rectangle(int i);

    /**
     * @deprecated Method pointCount is deprecated
     */

    public native int pointCount(int i);

    /**
     * @deprecated Method points is deprecated
     */

    public native Point[] points(int i);

    /**
     * @deprecated Method isHole is deprecated
     */

    public native boolean isHole(int i);

    public void capture(int width, int height)
    {
        capture(width, height, 0);
    }

    public native void capture(int i, int j, int k);

    public void movie(String filename)
    {
        loadMovie(filename, -1, -1);
    }

    public void movie(String filename, int width, int height)
    {
        loadMovie(filename, width, height);
    }

    /**
     * @deprecated Method loadMovie is deprecated
     */

    private native void loadMovie(String s, int i, int j);

    public native void read();

    public native float property(int i);

    public void jump(int millis)
    {
        jump(millis, 0);
    }

    public void jump(float ratio)
    {
        jump(Math.max(Math.min(ratio, 1.0F), 0.0F), 2);
    }

    public native void jump(float f, int i);

    public void ROI(Rectangle rect)
    {
        if(rect == null)
        {
            ROI(0, 0, width, height);
        } else
        {
            ROI(rect.x, rect.y, rect.width, rect.height);
        }
    }

    public native void ROI(int i, int j, int k, int l);

    public String absolutePath(String file)
    {
     
    	try{
    	String paths[] = new String[0];
        String pwd = parent == null ? System.getProperty("user.dir") + File.separator : parent.sketchPath("");
        String dat = parent == null ? pwd + "data" + File.separator : parent.dataPath("");
        String home = System.getProperty("user.home");
        if(OS.indexOf("windows") != -1)
        {
            String PATH[] = System.getProperty("java.library.path").split(";");
            int i = 0;
            do
            {
                if(i >= PATH.length)
                {
                    break;
                }
                if(PATH[i].indexOf("OpenCV") != -1)
                {
                    paths = (new String[] {
                        dat, pwd, PATH[i].replaceFirst("bin", "") + "data\\haarcascades\\"
                    });
                    break;
                }
                i++;
            } while(true);
        }
        if(OS.indexOf("mac") != -1)
        {
            paths = (new String[] {
                dat, pwd, "/System/Library/Frameworks/OpenCV.framework/Resources/", "/Library/Frameworks/OpenCV.framework/Resources/", home + "/Library/Frameworks/OpenCV.framework/Resources/"
            });
        }
        if(OS.indexOf("linux") != -1)
        {
            paths = (new String[] {
                dat, pwd, "/usr/share/opencv/haarcascades/", "/usr/local/share/opencv/haarcascades/"
            });
        }
        if((new File(file)).isAbsolute())
        {
            return file;
        }
        for(int i = 0; i < paths.length; i++)
        {
            if((new File(paths[i] + file)).exists())
            {
                return paths[i] + file;
            }
        }
        
        return getClass().getClassLoader().getResource(file).getPath();
    	}
    	catch(NullPointerException e){
    		return file;
    	}
    }

    public native void cascade(String s);

    public native Rectangle[] detect(float f, int i, int j, int k, int l);

    public Rectangle[] detect()
    {
        return detect(1.1F, 3, 0, 0, 0);
    }

    static 
    {
        OS = System.getProperty("os.name").toLowerCase();
        try
        {
            System.loadLibrary("OpenCV");
        }
        catch(UnsatisfiedLinkError e)
        {
            String err = "!!! required library not found : " + e.getMessage() + "\n" + "Verify that the java.library.path property is correctly set and ";
            if(OS.indexOf("mac") != -1)
            {
                err = err + "the opencv.framework exists in '/Library/Frameworks' folder";
            }
            if(OS.indexOf("windows") != -1)
            {
                err = err + "the '\\path\\to\\OpenCV\\bin' exists in your system PATH";
            }
            if(OS.indexOf("linux") != -1)
            {
                err = err + "'libcxcore.so', 'libcv.so', 'libcvaux.so', 'libml.so', and 'libhighgui.so' are p" +
"laced (or linked) in one of your system shared libraries folder"
;
            }
            System.err.println("\n" + err + "\n");
        }
    }
}
