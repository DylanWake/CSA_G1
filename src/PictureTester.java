/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
    Picture beach = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  /** Method to test keepOnlyRed */
  public static void testKeepOnlyRed()
  {
    Picture beach = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/beach.jpg");
    beach.explore();
    beach.keepOnlyRed();
    beach.explore();
  }

  /** Method to test keepOnlyGreen */
  public static void testKeepOnlyGreen()
  {
    Picture beach = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/beach.jpg");
    beach.explore();
    beach.keepOnlyGreen();
    beach.explore();
  }

  /** Method to test negate */
  public static void testNegate()
  {
    Picture beach = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }
  
  /** Method to test grayscale */
  public static void testGrayscale()
  {
    Picture beach = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/beach.jpg");
    beach.explore();
    beach.grayscale();
    beach.explore();
  }
  
  /** Method to test fixUnderwater */
  public static void testFixUnderwater()
  {
    Picture water = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/water.jpg");
    water.explore();
    water.fixUnderwater();
    water.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorVerticalRightToLeft */
  public static void testMirrorVerticalRightToLeft()
  {
    Picture beach = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/beach.jpg");
    beach.explore();
    beach.mirrorVerticalRightToLeft();
    beach.explore();
  }
  
  /** Method to test mirrorHorizontal */
  public static void testMirrorHorizontal()
  {
    Picture motorcycle = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/redMotorcycle.jpg");
    motorcycle.explore();
    motorcycle.mirrorHorizontal();
    motorcycle.explore();
  }
  
  /** Method to test mirrorHorizontalBotToTop */
  public static void testMirrorHorizontalBotToTop()
  {
    Picture motorcycle = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/redMotorcycle.jpg");
    motorcycle.explore();
    motorcycle.mirrorHorizontalBotToTop();
    motorcycle.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("C:/S3/CSA/Color Selection/Color_Selection/src/images/swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    testFixUnderwater();
    //testMirrorVertical();
    //testMirrorVerticalRightToLeft();
    //testMirrorHorizontal();
    //testMirrorHorizontalBotToTop();
    //testMirrorTemple();   
    //testCollage();
    //testEdgeDetection();
    
    //DigitalPicture p = new DigitalPicture();
    //DigitalPicture p1 = new SimplePicture();
    //DigitalPicture p = new Picture();
    //SimplePicture p2 = new Picture();
    //Picture p3 = new SimplePicture();
    
  }
}