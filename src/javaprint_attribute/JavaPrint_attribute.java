/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprint_attribute;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import javax.print.attribute.standard.OrientationRequested;

/**
 *
 * @author yusuke
 */
public class JavaPrint_attribute {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //プリントサービス一覧の取得
        PrintService[] myPrintServices = PrintServiceLookup.lookupPrintServices(null, null);
        
        for(int cnt=0; cnt < myPrintServices.length; cnt++){
            System.out.println("=====Print Service ["+cnt+"]=====");
            PrintService myPrintService = myPrintServices[cnt];
            PrintServiceAttributeSet attributeSet= myPrintService.getAttributes();
                    
            //プリントサービスの属性を出力
            Attribute[] attr=attributeSet.toArray();
            
            
            System.out.println( "-----Print Service Attribute-------");
            for(int i =0 ;i<attr.length;i++){
                System.out.println("   "+attr[i].getClass().toString()+"="+attr[i]+"'");                
            }
            //そのプリントサービスがサポートするフレーバーを出力
            DocFlavor[] glavors = myPrintService.getSupportedDocFlavors();
            
            DocFlavor flavor;
            System.out.println(" ----Print Servie Flavor-----");
            for(int i=0; i<glavors.length;i++){
                flavor=glavors[i];
                System.out.println("  "+flavor.toString());            
            }
            
            //RequestAttributeをセット
            PrintRequestAttributeSet attributes =new HashPrintRequestAttributeSet();
            attributes.add(OrientationRequested.PORTRAIT);
            attributes.add(new Copies(1));
            attributes.add(MediaTray.BOTTOM);
            attributes.add(MediaSizeName.ISO_A4);
            
                if(myPrintServices.length > 0){
                    Object values=myPrintServices[cnt].getSupportedAttributeValues(
                            Media.class, DocFlavor.INPUT_STREAM.JPEG, attributes);
                    if(values instanceof Media[]){
                        Media[] media=(Media[])values;

                        for(int i=0;i<media.length;i++){
                            Media medium =media[i];
                            if(medium instanceof MediaSizeName){
                                System.out.println("用紙");
                                
                            }else if(medium instanceof MediaTray){
                                System.out.println("トレイ");
                            }
                            System.out.println(i+":"+medium);

                        }
                    }
                }
                //PrintService service=ServiceUI.printDialog(null, 50, 50, myPrintServices,myPrintServices[0] , null, attributes);
        }
    }   
}
