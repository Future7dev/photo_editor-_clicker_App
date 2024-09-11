package webcam_test;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Scanner;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;



public class Webcam_test extends Application {
    private Stage s2_stage;
    double zmf=1.0;
    
     
    public void start(Stage stage) throws Exception {
        Font f=new Font("Times New Roman",20);
        Webcam web=Webcam.getDefault();
        
        Dimension d=new Dimension(640,480);
        web.setViewSize(d);
        web.open();
        
        
        Button b2=new Button("view");b2.setFont(f);
        Label l=new Label();
        Button b1=new Button("capture");b1.setFont(f);
        b1.setOnAction(o->{
            try{
            ImageIO.write(web.getImage(), "PNG", new File("vi.png"));
            ImageView icon=new ImageView(new Image(new FileInputStream("vi.png") ));
            icon.setFitHeight(40);
            icon.setFitWidth(40);
            b2.setGraphic(icon);
            }catch(Exception w){
                
            }
        });
        
        
        b1.setAlignment(Pos.CENTER);
        VBox vb=new VBox();vb.setAlignment(Pos.CENTER);
        
        MenuBar mb=new MenuBar();
        
        Menu file=new Menu("Files");file.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px;");
        MenuItem op=new MenuItem("open");op.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px;");
       
        
        
        
        MenuItem sa=new MenuItem("save");sa.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px;");
        
        
        
        file.getItems().addAll(op,sa);
        mb.getMenus().addAll(file);
        
        
        
        b2.setOnAction(u->{
            try{
            ImageView icon=new ImageView(new Image(new FileInputStream("vi.png") ));
            VBox vwb=new VBox();vwb.setAlignment(Pos.CENTER);
            Slider sl1=new Slider(-1,1,0);
            Slider sl2=new Slider(-1,1,0);
            Slider sl3=new Slider(-1,1,0);
            ColorAdjust colorAdjust = new ColorAdjust();
            sl1.valueProperty().addListener(t->{
                //ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(sl1.getValue());
                icon.setEffect(colorAdjust);
            
            });
            sl2.valueProperty().addListener(t->{
                //ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setContrast(sl2.getValue());
                icon.setEffect(colorAdjust);
            
            });
            sl3.valueProperty().addListener(t->{
               // ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setHue(sl3.getValue());
                icon.setEffect(colorAdjust);
            
            });
            
            
            HBox hb1=new HBox();
            hb1.getChildren().add(sl1);
            
            HBox hb2=new HBox();
            hb2.getChildren().add(sl2);
            
            HBox hb3=new HBox();
            hb3.getChildren().add(sl3);
            
            TitledPane brig=new TitledPane("Brightness",hb1);brig.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px;");
            TitledPane sat=new TitledPane("Saturation",hb2);sat.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px;");
            TitledPane hue=new TitledPane("Hue",hb3);hue.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px;");
            
            Accordion root =new Accordion();
            root.getPanes().addAll(brig,sat,hue);
            vwb.getChildren().addAll(icon,root);vwb.setSpacing(20);
            
            //s_stage =new Stage();
            
            
            
            BorderPane bp=new BorderPane();
            //bp.getChildren().addAll(mb,vwb);
            
            bp.setTop(mb);
            bp.setCenter(vwb);
            bp.setStyle("-fx-background-color: linear-gradient(to right, rgba(72, 209, 204, 0.7), rgba(123, 104, 238, 0.8))");
            Stage s_stage =new Stage();
            Scene nex=new Scene(bp,700,700);
            s_stage.setScene(nex);
            s_stage.show();
            
            sa.setOnAction(iu->{
            saveIm(icon,s_stage);
        });
            
            
            op.setOnAction(p->{
            FileChooser fc=new FileChooser();
            java.io.File file2=fc.showOpenDialog(s_stage);
            try{
                ImageView icon2=new ImageView(new Image(new FileInputStream(file2) ));
                icon2.setFitWidth(500); // Example width
                icon2.setFitHeight(400);
                icon2.setPreserveRatio(true);
               
                
                
                Scale scale =new Scale(zmf,zmf,0,0);
                Slider sl_2=new Slider(0,5,1);
                icon2.getTransforms().add(scale);
                sl_2.valueProperty().addListener(iy->{
                    //zmf*=sl_2.getValue();
                    scale.setX(sl_2.getValue());
                    scale.setY(sl_2.getValue());
                });
                
                
                HBox hwb2=new HBox();vwb.setAlignment(Pos.CENTER);
                hwb2.getChildren().addAll(icon2);hwb2.setSpacing(20);
                VBox vb4=new VBox();vb4.setAlignment(Pos.CENTER);
                vb4.getChildren().addAll(hwb2,sl_2);vb4.setSpacing(20);
                vb4.setStyle("-fx-background-color: linear-gradient(to bottom right, lightblue, yellow, violet, blue)");
                Scene thie=new Scene(vb4,500,500);
                
                s2_stage=new Stage();
                s2_stage.setScene(thie);
                s2_stage.show();
                
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        });
            
            
            
            }catch(Exception r){
                 r.printStackTrace();
            }
        
        });
        
        HBox hb= new HBox();
        hb.getChildren().addAll(b1,b2);hb.setAlignment(Pos.CENTER);hb.setSpacing(20);
        l.setStyle( "-fx-border-color: lightblue; " +
            "-fx-border-width: 8; " +
            "-fx-border-style: solid; " +
            "-fx-border-radius: 5; ");
        
        vb.getChildren().addAll(l,hb);vb.setSpacing(20);
        vb.setStyle( "-fx-border-color: blue; " +
            "-fx-border-width: 2; " +
            "-fx-border-style: solid; " +
            "-fx-border-radius: 5; " );
         

        //Image i=web.getImage()
//        ImageIO.write(web.getImage(), "PNG", new File("me.png"));
//        ImageView iv= new ImageView(new Image(new FileInputStream("me.png")));
//                
//        ScrollPane sp=new ScrollPane(iv);
//        
        
//        VBox vb=new VBox();
//        Label l=new Label("hi",iv);
//        //l.setg
//        vb.getChildren().add(sp);

        video v=new video(web,l);
        v.start();
        stage.setOnCloseRequest(event -> {
            v.stopRunning();
        });
        
        
        vb.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 50%, #FF7F50, #6A5ACD)");
        BorderPane bp1=new BorderPane();
        
        bp1.setTop(mb);
        bp1.setCenter(vb);
        Scene sc=new Scene(bp1,800,800);
        stage.setScene(sc);
        stage.show();
        
        
        
        
    }
    

        public static void main(String[] args)  {
            launch(args);
        
        
        
    }
    public void saveIm(ImageView im,Stage stage){
        double width = im.getBoundsInParent().getWidth();
    double height = im.getBoundsInParent().getHeight();
           WritableImage ima = new WritableImage((int)width, (int)height);
           im.snapshot(null, ima);
           FileChooser fc=new FileChooser();
           fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG","*png"));
           File file=fc.showSaveDialog(stage);
           if(file!=null){
           try{
               BufferedImage bf=SwingFXUtils.fromFXImage(ima, null);
               ImageIO.write(bf, "PNG", file);
           }catch(Exception r){
               r.printStackTrace();
           }
           }
          
        }

    
    
}
class video extends Thread{
    private volatile boolean running = true;
    Webcam web;
    Label l;
    public  video(Webcam webl,Label l1){
        
        web=webl;
        l=l1;
        
    }
    public void run(){
        while(running){
            try{
                BufferedImage bufferedImage = web.getImage();
                Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
                ImageView iv2=new ImageView(fxImage);
                iv2.setScaleX(-1);
                javafx.application.Platform.runLater(() -> l.setGraphic(iv2));
                Thread.sleep(50);
                
            }
            catch(Exception er){
                
            }
        }
    }
    public  void stopRunning() {
        running = false;
    }
}
