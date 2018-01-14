package miniproject;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
public class Editor extends JFrame implements ActionListener{

        JMenuBar mb;
        JMenu file,format;
        JMenuItem New,open,save,quit;
        JToolBar tb;
        JButton Newb,open1,save1;
        JComboBox fontsize,fontcolor,fontstyle;
		JButton copy1;
        JTextArea ta;
        JScrollPane scrollpane;
        
        Container c;
        String size[] = {"10","14","20","30"};
        String color[] = {"red","green","blue","white"};
        String style[] = {"arial","sans-serif","comic"};
        public Editor(){
        	mb = new JMenuBar();
        	file = new JMenu("file");
        	format = new JMenu("format");
        	open = new JMenuItem("Open");
        	New = new JMenuItem("New");
        	save = new JMenuItem("Save");
        	quit = new JMenuItem("quit");
        	tb = new JToolBar();
        	Newb = new JButton("new");
        	open1 = new JButton("open");
        	save1 = new JButton("save");
        	copy1 = new JButton("Copy");
        	fontsize = new JComboBox(size);
        	fontcolor = new JComboBox(color);
        	fontstyle = new JComboBox(style);
        	ta = new JTextArea(50,50);
        	scrollpane = new JScrollPane();
        	 c = this.getContentPane();
             c.setLayout(new FlowLayout());
             //this.setJMenuBar(mb);
             mb.add(file);
             mb.add(format);
             file.add(New);
             file.add(open);
             file.add(save);
             file.add(quit);
             c.add(mb);
             c.add(tb);
             tb.add(Newb);
             tb.add(open1);
             tb.add(save1);
             tb.add(copy1);
             tb.add(fontsize);
             tb.add(fontcolor);
             tb.add(fontstyle);
             ta.setLineWrap(true);
             ta.setWrapStyleWord(true);
             c.add(ta);
             save1.addActionListener(new ActionListener(){
            	 @Override
         		public void actionPerformed(ActionEvent ke) {
         			// TODO Auto-generated method stub
            		 JFileChooser file = new JFileChooser();
            		 //TextFilter filter = new TextFilter();
            		 //file.setFileFilter(filter);
            		 String fileName = "";
            		 // show save file dialog
            		 if (file.showSaveDialog(c) == JFileChooser.APPROVE_OPTION) {
            		 // get full path of selected file
            		 fileName = file.getSelectedFile().getAbsolutePath();
            		 // get meta of text
            		 PlainDocument doc = (PlainDocument) ta.getDocument();
            		 // convert to richtext format
            		 RTFEditorKit kit = new RTFEditorKit();
            		 BufferedOutputStream out;
            		 try {
            		 out = new BufferedOutputStream(new FileOutputStream(fileName));
            		 // save content to file
            		 kit.write(out, doc, doc.getStartPosition().getOffset(), doc.getLength());
            		 out.flush();
            		 out.close();
            		 } catch (Exception e) {
            		 System.out.println("Err:" + e.toString());
            		 }
            		  
            		 } else {
            		 return;
            		 }               		
            	 }
             });
             open1.addActionListener(new ActionListener(){
            	 @Override
         		public void actionPerformed(ActionEvent ke) {
         			// TODO Auto-generated method stub
                       /* JFileChooser fc = new JFileChooser(".");
                        int i=fc.showOpenDialog(c);
                        */
            		 JFileChooser file = new JFileChooser();
            		 //TextFilter filter = new TextFilter();
            		 //file.setFileFilter(filter);
            		 String fileName = "";
            		 // show open file dialog
            		 if (file.showOpenDialog(c) == JFileChooser.APPROVE_OPTION) {
            		 fileName = file.getSelectedFile().getAbsolutePath();
            		 } else {
            		 return;
            		 }
            		 // using richtext format
            		 RTFEditorKit rtf = new RTFEditorKit();
            		 try {
            		 // load file into jTextPane
            		 FileInputStream fi = new FileInputStream(fileName);
            		 rtf.read(fi, ta.getDocument(), 0);
            		 fi.close();
            		 } catch (Exception e) {
            		 System.out.println("err:" + e.toString());
            		 }	 
         		}
             });
             quit.addActionListener(new ActionListener(){
            	 @Override
         		public void actionPerformed(ActionEvent ke) {
         		           System.exit(0);	 
         		}
             });
             
             copy1.addActionListener(new ActionListener(){
            	 @Override
         		public void actionPerformed(ActionEvent ke) {
            		 StringSelection stringSelection = new StringSelection (ta.getText());
            		 Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
            		 clpbrd.setContents (stringSelection, null);	 
         		}
             });
             
             
             fontstyle.addActionListener(new ActionListener(){
            	 @Override
         		public void actionPerformed(ActionEvent ke) {
         			// TODO Auto-generated method stub
            		 GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            		// get all font name&amp;amp;amp;amp;amp;amp;amp;nbsp;
            		String[] fontNames = gEnv.getAvailableFontFamilyNames();
            		// load to combobox
            		ComboBoxModel model = new DefaultComboBoxModel(fontNames);
            		fontstyle.setModel(model);
            		
            		ta.setFont(new Font(fontstyle.getSelectedItem().toString(),
            		Font.PLAIN, Integer.parseInt(fontsize.getSelectedItem().toString())));
            		
            	
            	 }
             });
             
             
             
             Newb.addActionListener(new ActionListener(){
            	 @Override
         		public void actionPerformed(ActionEvent ke) {
         			// TODO Auto-generated method stub
            		ta.setText("");
            	
            	 }
             });
             
             
             
          
        }
        
        
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
        

}
