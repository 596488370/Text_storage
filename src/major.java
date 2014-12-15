import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class major extends JFrame{
	
	static create create ;
	static major main;
	
	static boolean isDraging;                    //判断窗体能否被拖动
	static int xx;
	static int yy;
	
	static JDialog dialog;                            //调节透明度的滑动器
	static JSlider slider=new JSlider(0,9,0);   
	    
	static JDialog d;                                          //新建、修改等操作的对话框
	static boolean flag ;                                  //判断io操作是否成功执行
	static JLabel label;
	static JTextField text;
	static JButton button;
	static JPanel panel;
	static JPanel panel1;
	
	static JList l1,l2;											//j是text的滚动面板
	static JTextArea t1;
	static JScrollPane j,j1,j2;
	static JSplitPane a, a1;
	
	static JPopupMenu pop = new JPopupMenu();
    static JMenuItem it1 = new JMenuItem("添加项目");
    static JMenuItem it2 = new JMenuItem("删除项目");
    static JMenuItem it3 = new JMenuItem("修改项目名");
    static JPopupMenu pop1 = new JPopupMenu();
    static JMenuItem ite1 = new JMenuItem("添加子文件");
    static JMenuItem ite2 = new JMenuItem("删除子文件");
    static JMenuItem ite3 = new JMenuItem("修改子文件名");
    static JPopupMenu pop2 = new JPopupMenu();
    static JMenuItem item1 = new JMenuItem("保存修改");
    static JMenuItem item2 = new JMenuItem("保存文件");
    static JMenuItem item3 = new JMenuItem("调节透明度");
    static JMenuItem item4 = new JMenuItem("置顶激活");
    
	static String p,pp;                                   // pp是选择到的文件夹路径;
	static String s[];
	
	static String p1,p2,p3;
	static String read;
	static String str1;                                 //得到修改保存的文件路径
	public major() throws IOException{
		l1 = new JList();                           //根据目录得到目录下级文件 s =major.get("dir");p和s
		l1.setFixedCellHeight(30);
		p = pp;            // JList 不实现直接滚动。要创建一个滚动的列表，请将它作为 JScrollPane 的视口视图。例如： 
		File filename;
		filename = new File(p);
        if (!filename.exists()) {
        	create = new create();
        	create.createDir(p);
        }
        
        /*构建l1、l2、t1及外观*/

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
        t1.setFont(new   Font( "微软雅黑",Font.PLAIN,18)); 
        t1.setLineWrap(true);//激活自动换行功能 ,用户体验                                     
        t1.setWrapStyleWord(true);//激活断行不断字功能 
        t1.setBackground(Color.DARK_GRAY);
        t1.setCaretColor(Color.green);
        t1.setSelectedTextColor(Color.blue);
        t1.setForeground (Color.ORANGE);
        //t1.getText().replaceAll("\n", "\r\n");
        /*完善界面*/
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
		//a.setPreferredSize (new Dimension (500,10));//设置大小
		//this.add(t1,BorderLayout.EAST);
		/*
		 * l1浮标功能      以及         l1事件监听
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
				label.setText("新增项目名：");
				button.setText("确定增加项目");
				d.setVisible(true);
			}
			
		});
		it2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("删除项目名：");
				button.setText("确定删除项目");
				if(p == pp){
					text.setText("请选择一个项目");
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
				label.setText("修改项目名：");
				button.setText("确定修改项目名");
				if(p == pp){
					text.setText("请选择一个项目");
				}
				else{
				text.setText(p);
				}
				d.setVisible(true);
			}
			
		});
		
		/*
		 * l2浮标功能      以及         l2事件监听
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
			str1 = p3;                                                                    //点击时赋予str1值，使保存充当修改 
			if(p1!=null){                                                                 //事件跳转时列表消失p1的值会被清空为null
			p2 = p ;
			p3 = p2+"/"+p1;
			
			try {
				read = major.read(p3);
				t1.setText(read);
				t1.setCaretPosition(0);                                              //使文本域光标显示第一行
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
				label.setText("新增子文件名：");
				if(p == pp){
					text.setText("请选择一个项目");
				}
				button.setText("确定增加子文件");
				d.setVisible(true);
			}
			
		});
		ite2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("删除子文件名：");
				button.setText("确定删除子文件");
				if(p3!=null){
				text.setText(p3);
				}
				else{
				text.setText("请选择删除子文件");
				}
				d.setVisible(true);
			}
			
		});
		ite3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("修改子文件名：");
				button.setText("确定修改子文件名");
				if(p3!=null){
					text.setText(p3);
					}
					else{
					text.setText("请选择要修改的子文件");
					}
				d.setVisible(true);
			}
			
		});
		/*
		 * t1的浮标     以及     事件响应
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
				label.setText("修改子文件名：");
				button.setText("确定修改子文件");
				text.setText(str1);
				d.setVisible(true);
				
			}
			
		});
		item2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("新建子文件名：");
				button.setText("确定保存子文件");
				text.setText(str1);
				d.setVisible(true);
				
			}
			
		});
		item3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//新建dialog创体
				setOpacity();
			}
			
		});
		item4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isAlwaysOnTop()){
				 main.setAlwaysOnTop(true);          //窗口置顶激活
				 item4.setText("取消置顶");
				}else{
					main.setAlwaysOnTop(false);          //窗口置顶取消
					 item4.setText("激活置顶");
					
				}
				 
			}
			
		});
		//Image icon = this.getToolkit().getImage("storage.png");      
	    //this.setIconImage(icon);
		this.setIconImage(new ImageIcon("storage.png").getImage());          //设置窗体图标不能使用ico
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("storage.png")));
		this.add(a1);
		this.setSize(720,650);
		//this.setSize(820,680);
		this.setLocation(500, 150);
		this.setTitle("Text_storage");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                  
		this.setUndecorated(true);                                                         //设置窗体透明，可控制
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



















	
	
	
	
	
	
	
	
	
	//主程序里需要调用函数
	/*
	 * 遍历文件
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
	 * 根据文件路径读取文件到文本域
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
                 a = a + read+ "\r\n";                                   //用于识别记事本里的换行
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
	 * 把文本域的数据写入到文件
	 * 使用字符输入流写入
	 * 需要File文件名
	 */
	/*
	 * 有一个bug， 每次保存时会在每一行加一个换行符，不便于下次修改保存
	 */
	 public static void writeTxtFile(String newStr,String path) throws IOException{
	        //先读取原有文件内容，然后进行写入操作
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
	            // TODO 自动生成 catch 块
	            e1.printStackTrace();
	        } 
	        
	    }
	 
	 /*
	  * 鼠标响应拖动
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
	 * 显现对话框
	 * 
	 * 
	 */
	public void dialog(){
		d = new JDialog(this,"对话框",true);
		label = new JLabel("文件名：");
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
    			String str = e.getActionCommand();                                      //依据按钮文字不同响应不同的事件操作
    			if(str.equals("确定增加项目")){                                              //可以持续响应
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
    			else if(str.equals("确定删除项目")){
    				 str1 = p; 
    				create= new create();
    				flag = create.deleteDirectory(str1);
    				if(flag = true){
    					text.setText("删除项目成功");
    				}
    				else{
    				text.setText("error");
    				}
    				p = pp;
    				s =	major.get(p);
    				l1.setListData(s);
    				l1.setSelectedIndex(0);
    			}
    			else if(str.equals("确定增加子文件")){
    				str1 = text.getText();
    				str1 = p+"/"+str1 ;
    				create= new create();
    				flag = create.createFile(str1);
    				text.setText(null);
    				s =	major.get(p);
    				l2.setListData(s);
    				t1.setText(null);
    			}
    			else if(str.equals("确定删除子文件")){
    				//p3 = "G:/Document/storage/a/a.txt";
    				// str1 = p3;
    				create= new create();
    				flag = create.deleteFile(str1);
    				if(flag = true){
    					text.setText("删除子文件成功");
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
    			else if(str.equals("确定修改子文件")){
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
						text.setText("成功修改子文件");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	            */
    				//String str2 = t1.getText().replaceAll("\n", "\r\n");           //替换文本域的换行符使记事本能够识别    
    				String str2 = t1.getText();
    				try {
						major.writeTxtFile(str2, str1);                           //使用类名调用函数
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				text.setText("成功修改子文件");
    				
    				
    			}
    			else if(str.equals("确定保存子文件")){             //新建子文件，才传递str1子路径目标保存，点击l2才传递p3的值，不点击p3无值，保存可以当成修改使用，修改也可以当成保存使用（发生了点击也可以）
    				//String str2 = t1.getText().replaceAll("\n", "\r\n");                                     //text出现值与路径一直一致对应   
    				String str2 = t1.getText();
    	            //byte[] b = str2.getBytes();                                              //输出流实现文本域向硬盘文件的数据输出
    				try {
						major.writeTxtFile(str2, str1);                           //使用类名调用函数
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				text.setText("成功保存子文件");
    				
    			}
    			else if(str.equals("确定修改项目名")){
    				 str1 = text.getText();
     				create= new create();
     				flag = create.updateFile(p, str1);
     				if(flag = true){
     					text.setText("修改项目名成功");
     				}
     				else{
     				text.setText("error");
     				}
     				p = pp;
     				s =	major.get(p);
     				l1.setListData(s);
     				l1.setSelectedIndex(0);
   			}
    			else if(str.equals("确定修改子文件名")){
    				 //str1 = p3;
    				 str1 = text.getText();
     				create= new create();
     				flag = create.updateFile(p3,str1 );
     				if(flag = true){
     					text.setText("修改子文件名成功");
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
	 * 调节透明度的窗体，响应这个函数实现直接对主窗体的控制
	 * 
	 */
	public void setOpacity(){
		//新建dialog创体
		dialog=new JDialog(this,"调整透明度",false);
		//设置大小和大小不可改变及位置
		dialog.setSize(270,100);
		dialog.setResizable(false);
		Dimension thisSize=this.getSize();
		Point thisPoint=this.getLocation();	
		Dimension diaSize=dialog.getSize();
		dialog.setLocation(thisPoint.x+(thisSize.width-diaSize.width)/2,
				thisPoint.y+(thisSize.height-diaSize.height)/2);
		//创建JLabel
		JLabel label=new JLabel("透明度:");
		
		//为此类的属性成员slider添加滑块移动事件
		slider.addChangeListener(new ChangeListener(){

			public void stateChanged(ChangeEvent arg0) {

				slider();
			}

		});
		//Panel中的控件自左往右布局
		JPanel upPanel=new JPanel(new FlowLayout());
		//将label和slider加入到upPanel中
		upPanel.add(label);
		upPanel.add(slider);
		//最后将upPanel加入到窗体中
		dialog.add(upPanel,BorderLayout.CENTER);

		dialog.setVisible(true);
	}
	
	public void slider(){
		
		//获取slider的值
		double value=slider.getValue()/10.0;
		//判断是否支持透明度
		if(	com.sun.awt.AWTUtilities.isWindowOpaque(this)){
			//设置主窗体的透明度
			com.sun.awt.AWTUtilities.setWindowOpacity(this, (float)(1- value));
			//设置滑块鼠标移入时的显示方式
			slider.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else{
			JOptionPane.showMessageDialog(this, "系统不支持、JDK版本过低或JRE系统库文件缺损!");
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//pp = "G:/Documents/storage";
		pp = null;                                                                          //若不设路径则选路径
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
