import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class major extends JFrame{
	
	static create create ;
	static major main;
	
	static boolean isDraging;                    //�жϴ����ܷ��϶�
	static int xx;
	static int yy;
	
	static JDialog dialog;                            //����͸���ȵĻ�����
	static JSlider slider=new JSlider(0,9,0);   
	    
	static JDialog d;                                          //�½����޸ĵȲ����ĶԻ���
	static boolean flag ;                                  //�ж�io�����Ƿ�ɹ�ִ��
	static JLabel label;
	static JTextField text;
	static JButton button;
	static JPanel panel;
	static JPanel panel1;
	
	static JList l1,l2;											//j��text�Ĺ������
	static JTextArea t1;
	static JScrollPane j,j1,j2;
	static JSplitPane a, a1;
	
	static JPopupMenu pop = new JPopupMenu();
    static JMenuItem it1 = new JMenuItem("�����Ŀ");
    static JMenuItem it2 = new JMenuItem("ɾ����Ŀ");
    static JMenuItem it3 = new JMenuItem("�޸���Ŀ��");
    static JPopupMenu pop1 = new JPopupMenu();
    static JMenuItem ite1 = new JMenuItem("������ļ�");
    static JMenuItem ite2 = new JMenuItem("ɾ�����ļ�");
    static JMenuItem ite3 = new JMenuItem("�޸����ļ���");
    static JPopupMenu pop2 = new JPopupMenu();
    static JMenuItem item1 = new JMenuItem("�����޸�");
    static JMenuItem item2 = new JMenuItem("�����ļ�");
    static JMenuItem item3 = new JMenuItem("����͸����");
    static JMenuItem item4 = new JMenuItem("�ö�����");
    
	static String p,pp;                                   // pp��ѡ�񵽵��ļ���·��;
	static String s[];
	
	static String p1,p2,p3;
	static String read;
	static String str1;                                 //�õ��޸ı�����ļ�·��
	public major() throws IOException{
		l1 = new JList();                           //����Ŀ¼�õ�Ŀ¼�¼��ļ� s =major.get("dir");p��s
		l1.setFixedCellHeight(30);
		p = pp;            // JList ��ʵ��ֱ�ӹ�����Ҫ����һ���������б��뽫����Ϊ JScrollPane ���ӿ���ͼ�����磺 
		File filename;
		filename = new File(p);
        if (!filename.exists()) {
        	create = new create();
        	create.createDir(p);
        }
        
        /*����l1��l2��t1�����*/

		//l1.setSelectedIndex(0);
		s =	major.get(p);
		l1.setListData(s);
		l2 = new JList();
		l2.setFixedCellHeight(40);
		l1.setSelectionBackground(Color.black);
		l1.setSelectionForeground(Color.cyan);
		l1.setBackground(Color.pink);
		l2.setSelectionBackground(Color.black);
		l2.setSelectionForeground(Color.cyan);
		l2.setBackground(Color.yellow);
		
		
		j1 = new JScrollPane(l1);
		j2 = new JScrollPane(l2);
		pop.add(it1);
		pop.add(it2);
		pop.add(it3);
		pop1.add(ite1);
		pop1.add(ite2);
		pop1.add(ite3);
		pop2.add(item1);
		pop2.add(item2);
		pop2.add(item3);
		pop2.add(item4);
		
		
		t1 = new JTextArea();     
		//t1.setTabSize(10); 
        t1.setFont(new   Font( "΢���ź�",Font.PLAIN,18)); 
        t1.setLineWrap(true);//�����Զ����й��� ,�û�����                                     
        t1.setWrapStyleWord(true);//������в����ֹ��� 
        t1.setBackground(Color.DARK_GRAY);
        t1.setCaretColor(Color.green);
        t1.setSelectedTextColor(Color.blue);
        t1.setForeground (Color.ORANGE);
        //t1.getText().replaceAll("\n", "\r\n");
        /*���ƽ���*/
		j = new JScrollPane(t1);
		a = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,j1,j2);
		a1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,a,j);
		a.setOneTouchExpandable(true);
		a1.setOneTouchExpandable(true);
		a.setContinuousLayout(true);
		a1.setContinuousLayout(true);
		a.setDividerSize(8);
		a.setDividerLocation(50);
		//a.setDividerLocation(70);
		a1.setDividerLocation(150);
		//a1.setDividerLocation(180);
		a1.setDividerSize(8);
		//a.setPreferredSize (new Dimension (500,10));//���ô�С
		//this.add(t1,BorderLayout.EAST);
		/*
		 * l1���깦��      �Լ�         l1�¼�����
		 * 
		 */
		
		l1.addMouseListener( new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				
				if (MouseEvent.BUTTON3 == e.getButton())
	            {
	                pop.show(l1, e.getPoint().x, e.getPoint().y);
	            }
				 
				
			}
		}
		);
		l1.addListSelectionListener(new  ListSelectionListener(){                      

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
			p =	(String) l1.getSelectedValue();
			p = pp+"/"+p  ;
			s =	major.get(p);
			l2.setListData(s);
			//l2.setSelectedIndex(0);
			//t1.setText(p3);
				
			}
			
		}
		);
		it1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("������Ŀ����");
				button.setText("ȷ��������Ŀ");
				d.setVisible(true);
			}
			
		});
		it2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("ɾ����Ŀ����");
				button.setText("ȷ��ɾ����Ŀ");
				if(p == pp){
					text.setText("��ѡ��һ����Ŀ");
				}
				else{
				text.setText(p);
				}
				d.setVisible(true);
			}
			
		});
		it3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("�޸���Ŀ����");
				button.setText("ȷ���޸���Ŀ��");
				if(p == pp){
					text.setText("��ѡ��һ����Ŀ");
				}
				else{
				text.setText(p);
				}
				d.setVisible(true);
			}
			
		});
		
		/*
		 * l2���깦��      �Լ�         l2�¼�����
		 * 
		*/
		l2.addMouseListener( new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				
				if (MouseEvent.BUTTON3 == e.getButton())
	            {
	                pop1.show(l2, e.getPoint().x, e.getPoint().y);
	            }
				 
				
			}
		}
		);
		l2.addListSelectionListener(new  ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
			p1 =	(String) l2.getSelectedValue();
			str1 = p3;                                                                    //���ʱ����str1ֵ��ʹ����䵱�޸� 
			if(p1!=null){                                                                 //�¼���תʱ�б���ʧp1��ֵ�ᱻ���Ϊnull
			p2 = p ;
			p3 = p2+"/"+p1;
			
			try {
				read = major.read(p3);
				t1.setText(read);
				t1.setCaretPosition(0);                                              //ʹ�ı�������ʾ��һ��
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}}
				
			}
			
		}
		);
		ite1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("�������ļ�����");
				if(p == pp){
					text.setText("��ѡ��һ����Ŀ");
				}
				button.setText("ȷ���������ļ�");
				d.setVisible(true);
			}
			
		});
		ite2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("ɾ�����ļ�����");
				button.setText("ȷ��ɾ�����ļ�");
				if(p3!=null){
				text.setText(p3);
				}
				else{
				text.setText("��ѡ��ɾ�����ļ�");
				}
				d.setVisible(true);
			}
			
		});
		ite3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("�޸����ļ�����");
				button.setText("ȷ���޸����ļ���");
				if(p3!=null){
					text.setText(p3);
					}
					else{
					text.setText("��ѡ��Ҫ�޸ĵ����ļ�");
					}
				d.setVisible(true);
			}
			
		});
		/*
		 * t1�ĸ���     �Լ�     �¼���Ӧ
		 * 
		 * 
		 */
		t1.addMouseListener( new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				
				if (MouseEvent.BUTTON3 == e.getButton())
	            {
	                pop2.show(t1, e.getPoint().x, e.getPoint().y);
	            }
				 
				
			}
		}
		);
		item1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("�޸����ļ�����");
				button.setText("ȷ���޸����ļ�");
				text.setText(str1);
				d.setVisible(true);
				
			}
			
		});
		item2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("�½����ļ�����");
				button.setText("ȷ���������ļ�");
				text.setText(str1);
				d.setVisible(true);
				
			}
			
		});
		item3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//�½�dialog����
				setOpacity();
			}
			
		});
		item4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isAlwaysOnTop()){
				 main.setAlwaysOnTop(true);          //�����ö�����
				 item4.setText("ȡ���ö�");
				}else{
					main.setAlwaysOnTop(false);          //�����ö�ȡ��
					 item4.setText("�����ö�");
					
				}
				 
			}
			
		});
		//Image icon = this.getToolkit().getImage("storage.png");      
	    //this.setIconImage(icon);
		this.setIconImage(new ImageIcon("storage.png").getImage());          //���ô���ͼ�겻��ʹ��ico
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("storage.png")));
		this.add(a1);
		this.setSize(720,650);
		//this.setSize(820,680);
		this.setLocation(500, 150);
		this.setTitle("Text_storage");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                  
		this.setUndecorated(true);                                                         //���ô���͸�����ɿ���
		this.setVisible(true);
		//com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.5F);
		//t1.addMouseListener(new mouse());
		//t1.addMouseMotionListener(new mouse1());
		l1.addMouseListener(new mouse());
		l1.addMouseMotionListener(new mouse1());
		l2.addMouseListener(new mouse());
		l2.addMouseMotionListener(new mouse1());
		//l1.setOpaque(false);
		//l2.setOpaque(false);
		//t1.setOpaque(false);
		//j.setOpaque(false);
		//j1.setOpaque(false);
		//j2.setOpaque(false);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



















	
	
	
	
	
	
	
	
	
	//����������Ҫ���ú���
	/*
	 * �����ļ�
	 * 
	 * 
	 */
	public static String[] get(String path){
		create = new create();
	int t =	create.getFilesint(path);
	String str[];
	str =	create.getFiles(path, t);
	return str;
		
	}
	
	
	/*
	 * �����ļ�·����ȡ�ļ����ı���
	 * 
	 * 
	 */
	public static String read(String path) throws IOException{
	/*FileInputStream fis = new FileInputStream(path);
	byte b[] = new byte[128];
	int length = 0;
	String text = "";
	while((length = fis.read(b)) != -1) {
	    text += new String(b,0,length);
	}
	fis.close();
	return text;
	*/
	 String read;
     String a ="";
     FileReader fileread;
     File filename;
     BufferedReader bufread;
     try {
     	filename = new File(path);
         fileread = new FileReader(filename);
         bufread = new BufferedReader(fileread);
         try {
             while ((read = bufread.readLine()) != null) {
                 a = a + read+ "\r\n";                                   //����ʶ����±���Ļ���
                 //readStr = readStr + read;
                 //fileread.close();
                 //bufread.close();
      
             }
             fileread.close();
             bufread.close();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
     return a;
	}
	/*
	 * ���ı��������д�뵽�ļ�
	 * ʹ���ַ�������д��
	 * ��ҪFile�ļ���
	 */
	/*
	 * ��һ��bug�� ÿ�α���ʱ����ÿһ�м�һ�����з����������´��޸ı���
	 */
	 public static void writeTxtFile(String newStr,String path) throws IOException{
	        //�ȶ�ȡԭ���ļ����ݣ�Ȼ�����д�����
	        //String filein = newStr + "\r\n" + readStr + "\r\n";
	        //RandomAccessFile mm = null;
		    File filename;
	    	FileWriter out;
	    	BufferedWriter bufwriter;
	    	//t1.getText().replaceAll("\n", "\r\n");
	        try {
	        	filename = new File(path);
	        	out = new FileWriter(filename);
	        	bufwriter = new BufferedWriter(out);
	        	bufwriter.write(newStr);
	        	bufwriter.close();
	        	out.close();
	        	/*while(bufread.ready()){
	        		bufwriter.write(newStr);
	        	}*/
	            //mm = new RandomAccessFile(filename, "rw");
	            //mm.writeBytes(filein);
	        } catch (IOException e1) {
	            // TODO �Զ����� catch ��
	            e1.printStackTrace();
	        } 
	        
	    }
	 
	 /*
	  * �����Ӧ�϶�
	  * 
	  * 
	  * 
	  */
	 class mouse implements MouseListener {     

			
 
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			isDraging = true;     
            xx = e.getX();     
            yy = e.getY();     
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			isDraging = false;     
			
		}     
	 }
	 class mouse1 implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
            if (isDraging) {     
                int left = getLocation().x;     
                int top = getLocation().y;     
                setLocation(left + e.getX() - xx, top + e.getY() - yy);     
            }     
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		 
	 }
	
	
	/*
	 * ���ֶԻ���
	 * 
	 * 
	 */
	public void dialog(){
		d = new JDialog(this,"�Ի���",true);
		label = new JLabel("�ļ�����");
		text = new JTextField(10);
		button = new JButton();
		panel = new JPanel();
		panel1 = new JPanel();
		d.setBounds(400,200 , 240, 100);
		panel.add(button);
		d.add(panel,BorderLayout.SOUTH);
		panel1.add(label);
		panel1.add(text);
		panel1.setLayout(new GridLayout(1,2));
		d.add(panel1,BorderLayout.NORTH);
		d.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					 d.setVisible(false);
					 text.setText(null);
					
		 }
		 });
		 
		 
		 
		 button.addActionListener(new ActionListener(){                                                     
    		 public void actionPerformed(ActionEvent e){
    			String str = e.getActionCommand();                                      //���ݰ�ť���ֲ�ͬ��Ӧ��ͬ���¼�����
    			if(str.equals("ȷ��������Ŀ")){                                              //���Գ�����Ӧ
    				create= new create();
    				p = pp;
    				str1=text.getText();
    				str1 = p+"/"+str1 ;
    				create.createDir(str1);
    				text.setText(null);
    				p = pp;
    				s =	major.get(p);
    				l1.setListData(s);
    			}
    			else if(str.equals("ȷ��ɾ����Ŀ")){
    				 str1 = p; 
    				create= new create();
    				flag = create.deleteDirectory(str1);
    				if(flag = true){
    					text.setText("ɾ����Ŀ�ɹ�");
    				}
    				else{
    				text.setText("error");
    				}
    				p = pp;
    				s =	major.get(p);
    				l1.setListData(s);
    				l1.setSelectedIndex(0);
    			}
    			else if(str.equals("ȷ���������ļ�")){
    				str1 = text.getText();
    				str1 = p+"/"+str1 ;
    				create= new create();
    				flag = create.createFile(str1);
    				text.setText(null);
    				s =	major.get(p);
    				l2.setListData(s);
    				t1.setText(null);
    			}
    			else if(str.equals("ȷ��ɾ�����ļ�")){
    				//p3 = "G:/Document/storage/a/a.txt";
    				// str1 = p3;
    				create= new create();
    				flag = create.deleteFile(str1);
    				if(flag = true){
    					text.setText("ɾ�����ļ��ɹ�");
    					//text.setText(p3);
    					//t1.setText(p);
    				}
    				else{
    				text.setText("error");
    				}
    				//t1.setText(p3 +"  "+ p);
    				s =	major.get(p);
    				l2.setListData(s);
    				t1.setText(null);
    			}
    			else if(str.equals("ȷ���޸����ļ�")){
    				/*String str2 = t1.getText();
    	            byte[] b = str2.getBytes();
    	            FileOutputStream fos = null;
					try {
						//str1 = p3;
						fos = new FileOutputStream(str1);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	            try {
						fos.write(b);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	            try {
						fos.close();
						text.setText("�ɹ��޸����ļ�");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	            */
    				//String str2 = t1.getText().replaceAll("\n", "\r\n");           //�滻�ı���Ļ��з�ʹ���±��ܹ�ʶ��    
    				String str2 = t1.getText();
    				try {
						major.writeTxtFile(str2, str1);                           //ʹ���������ú���
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				text.setText("�ɹ��޸����ļ�");
    				
    				
    			}
    			else if(str.equals("ȷ���������ļ�")){             //�½����ļ����Ŵ���str1��·��Ŀ�걣�棬���l2�Ŵ���p3��ֵ�������p3��ֵ��������Ե����޸�ʹ�ã��޸�Ҳ���Ե��ɱ���ʹ�ã������˵��Ҳ���ԣ�
    				//String str2 = t1.getText().replaceAll("\n", "\r\n");                                     //text����ֵ��·��һֱһ�¶�Ӧ   
    				String str2 = t1.getText();
    	            //byte[] b = str2.getBytes();                                              //�����ʵ���ı�����Ӳ���ļ����������
    				try {
						major.writeTxtFile(str2, str1);                           //ʹ���������ú���
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				text.setText("�ɹ��������ļ�");
    				
    			}
    			else if(str.equals("ȷ���޸���Ŀ��")){
    				 str1 = text.getText();
     				create= new create();
     				flag = create.updateFile(p, str1);
     				if(flag = true){
     					text.setText("�޸���Ŀ���ɹ�");
     				}
     				else{
     				text.setText("error");
     				}
     				p = pp;
     				s =	major.get(p);
     				l1.setListData(s);
     				l1.setSelectedIndex(0);
   			}
    			else if(str.equals("ȷ���޸����ļ���")){
    				 //str1 = p3;
    				 str1 = text.getText();
     				create= new create();
     				flag = create.updateFile(p3,str1 );
     				if(flag = true){
     					text.setText("�޸����ļ����ɹ�");
     					//text.setText(p3);
     					//t1.setText(p);
     				}
     				else{
     				text.setText("error");
     				}
     				//t1.setText(p3 +"  "+ p);
     				s =	major.get(p);
     				l2.setListData(s);
     				//t1.setText(null);
   			}
        }
      });
	}
	/*
	 * ����͸���ȵĴ��壬��Ӧ�������ʵ��ֱ�Ӷ�������Ŀ���
	 * 
	 */
	public void setOpacity(){
		//�½�dialog����
		dialog=new JDialog(this,"����͸����",false);
		//���ô�С�ʹ�С���ɸı估λ��
		dialog.setSize(270,100);
		dialog.setResizable(false);
		Dimension thisSize=this.getSize();
		Point thisPoint=this.getLocation();	
		Dimension diaSize=dialog.getSize();
		dialog.setLocation(thisPoint.x+(thisSize.width-diaSize.width)/2,
				thisPoint.y+(thisSize.height-diaSize.height)/2);
		//����JLabel
		JLabel label=new JLabel("͸����:");
		
		//Ϊ��������Գ�Աslider��ӻ����ƶ��¼�
		slider.addChangeListener(new ChangeListener(){

			public void stateChanged(ChangeEvent arg0) {

				slider();
			}

		});
		//Panel�еĿؼ��������Ҳ���
		JPanel upPanel=new JPanel(new FlowLayout());
		//��label��slider���뵽upPanel��
		upPanel.add(label);
		upPanel.add(slider);
		//���upPanel���뵽������
		dialog.add(upPanel,BorderLayout.CENTER);

		dialog.setVisible(true);
	}
	
	public void slider(){
		
		//��ȡslider��ֵ
		double value=slider.getValue()/10.0;
		//�ж��Ƿ�֧��͸����
		if(	com.sun.awt.AWTUtilities.isWindowOpaque(this)){
			//�����������͸����
			com.sun.awt.AWTUtilities.setWindowOpacity(this, (float)(1- value));
			//���û����������ʱ����ʾ��ʽ
			slider.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else{
			JOptionPane.showMessageDialog(this, "ϵͳ��֧�֡�JDK�汾���ͻ�JREϵͳ���ļ�ȱ��!");
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//pp = "G:/Documents/storage";
		pp = null;                                                                          //������·����ѡ·��
		if (pp != null) {
			main = new major();
			main.dialog();
		} else {
			JFileChooser c = new JFileChooser();
			c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			c.setDialogTitle("Select path to open");
			int result = c.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
			pp = c.getSelectedFile().getAbsolutePath();
			//System.out.print(pp);
			main = new major();
			main.dialog();
		}
		
		}
		

		
	}
	
}
