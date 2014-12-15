import java.io.*;
import java.util.ArrayList;



public class create {
	
	private static ArrayList<String> filelist = new ArrayList<String>();
	
	    // ��֤�ַ����Ƿ�Ϊ��ȷ·������������ʽ
	    private static final String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	    // ͨ�� sPath.matches(matches) �����ķ���ֵ�ж��Ƿ���ȷ
	    // sPath Ϊ·���ַ���
	   public boolean flag = false;
	    static File file;
	    private static File source ;
		private static File dest;

	    public  boolean DeleteFolder(String deletePath) {// ����·��ɾ��ָ����Ŀ¼���ļ������۴������
	        flag = false;
	        if (deletePath.matches(matches)) {
	            file = new File(deletePath);
	            if (!file.exists()) {// �ж�Ŀ¼���ļ��Ƿ����
	                return flag; // �����ڷ��� false
	            } else {

	                if (file.isFile()) {// �ж��Ƿ�Ϊ�ļ�
	                    return deleteFile(deletePath);// Ϊ�ļ�ʱ����ɾ���ļ�����
	                } else {
	                    return deleteDirectory(deletePath);// ΪĿ¼ʱ����ɾ��Ŀ¼����
	                }
	            }
	        } else {
	         //   System.out.println("Ҫ������ȷ·����");
	            return false;
	        }
	    }

	    public boolean deleteFile(String filePath) {// ɾ�������ļ�
	        flag = false;
	        file = new File(filePath);
	        if (file.isFile()) {// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
	            file.delete();// �ļ�ɾ��
	            flag = true;
	        }
	        
	        return flag;
	    }

	    public  boolean deleteDirectory(String dirPath) {// ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ�
	        // ���sPath�����ļ��ָ�����β���Զ�����ļ��ָ���
	        if (!dirPath.endsWith(File.separator)) {
	            dirPath = dirPath + File.separator;
	        }
	        File dirFile = new File(dirPath);
	        // ���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�
	        if (!dirFile.exists() || !dirFile.isDirectory()) {
	            return false;
	        }
	        flag = true;
	        File[] files = dirFile.listFiles();// ��ô���·���µ������ļ�
	        for (int i = 0; i < files.length; i++) {// ѭ������ɾ���ļ����µ������ļ�(������Ŀ¼)
	            if (files[i].isFile()) {// ɾ�����ļ�
	                flag = deleteFile(files[i].getAbsolutePath());
	              //  System.out.println(files[i].getAbsolutePath() + " ɾ���ɹ�");
	                if (!flag)
	                    break;// ���ɾ��ʧ�ܣ�������
	            } else {// ���õݹ飬ɾ����Ŀ¼
	                flag = deleteDirectory(files[i].getAbsolutePath());
	                if (!flag)
	                    break;// ���ɾ��ʧ�ܣ�������
	            }
	        }
	        if (!flag)
	            return false;
	        if (dirFile.delete()) {// ɾ����ǰĿ¼
	            return true;
	        } else {
	            return false;
	        }
	    }

	    // ���������ļ�
	    public  boolean createFile(String filePath) {
	        File file = new File(filePath);
	        if (file.exists()) {// �ж��ļ��Ƿ����
	           // System.out.println("Ŀ���ļ��Ѵ���" + filePath);
	        	
	            return false;
	        }
	        if (filePath.endsWith(File.separator)) {// �ж��ļ��Ƿ�ΪĿ¼
	           // System.out.println("Ŀ���ļ�����ΪĿ¼��");
	            return false;
	        }
	        if (!file.getParentFile().exists()) {// �ж�Ŀ���ļ����ڵ�Ŀ¼�Ƿ����
	            // ���Ŀ���ļ����ڵ��ļ��в����ڣ��򴴽����ļ��� www.2cto.com
	          //  System.out.println("Ŀ���ļ�����Ŀ¼�����ڣ�׼����������");
	            if (!file.getParentFile().mkdirs()) {// �жϴ���Ŀ¼�Ƿ�ɹ�
	               // System.out.println("����Ŀ���ļ����ڵ�Ŀ¼ʧ�ܣ�");
	                return false;
	            }
	        }
	        try {
	            if (file.createNewFile()) {// ����Ŀ���ļ�
	               // System.out.println("�����ļ��ɹ�:" + filePath);
	                return true;
	            } else {
	               // System.out.println("�����ļ�ʧ�ܣ�");
	                return false;
	            }
	        } catch (IOException e) {// �����쳣
	            e.printStackTrace();
	           // System.out.println("�����ļ�ʧ�ܣ�" + e.getMessage());
	            return false;
	        }
	    }

	    // ����Ŀ¼
	    public  boolean createDir(String destDirName) {
	        File dir = new File(destDirName);
	        if (dir.exists()) {// �ж�Ŀ¼�Ƿ����
	           // System.out.println("����Ŀ¼ʧ�ܣ�Ŀ��Ŀ¼�Ѵ��ڣ�");
	            return false;
	        }
	        if (!destDirName.endsWith(File.separator)) {// ��β�Ƿ���"/"����
	            destDirName = destDirName + File.separator;
	        }
	        if (dir.mkdirs()) {// ����Ŀ��Ŀ¼
	         //   System.out.println("����Ŀ¼�ɹ���" + destDirName);
	            return true;
	        } else {
	       //     System.out.println("����Ŀ¼ʧ�ܣ�");
	            return false;
	        }
	    }
	    
	    
	    
	    
	    /*
	     * �����ļ����ڵ������ļ�
	     * ���õݹ�
	     * 
	     */
	    
	    
	    public String getFilesfile(String filePath){
	    	String str = null;
	    	  file = new File(filePath);
	    	    File[] files = file.listFiles();
	    	    for(File file:files){     
	    	     if(file.isDirectory()){
	    	      /*
	    	       * �ݹ����
	    	       */
	    	     getFilesfile(file.getAbsolutePath());
	    	      filelist.add(file.getAbsolutePath());
	    	      //System.out.println("��ʾ"+filePath+"��������Ŀ¼�����ļ�"+file.getAbsolutePath()+file.getName());
	    	      System.out.println(file.getName());
	    	      //str = file.getName();
	    	     }
	    	     else{
	    	     // System.out.println("��ʾ"+filePath+"��������Ŀ¼"+file.getAbsolutePath()+file.getName()+file.lastModified());
	    	      System.out.println(file.getName());
	    	    	//str = file.getName();
	    	     }     
	    	    }
	    	    return str;
	    }
	    
	    
	    
	    
	    /*
	     * ������һ����Ŀ¼���õ���Ŀ¼��                              
	     * ������Ŀ¼���õ���Ŀ¼�µ��ļ���
	     * 
	     */
	
	    
	 

		public String[] getFiles(String filePath,int i){ 
			
	    	String[] str = new String[i] ;
	    	 i = 0;
	    	  file = new File(filePath);
	    	    File[] files = file.listFiles();
	    	    for(File file:files){     
	    	
	    	      //System.out.println("��ʾ"+filePath+"��������Ŀ¼"+file.getAbsolutePath()+file.getName()+file.lastModified());
	    	    	str[i] = file.getName();
	    	    //	str[i] = file.getAbsolutePath();
	    	    	i=i+1;
	    	         
	    	    }
	    	    return str;
	    }
	    /*
	     * �õ�һ��Ŀ¼���ļ�����
	     * 
	     * 
	     */
		
		
         public int getFilesint(String filePath){ 
	    	int i = 0;
	    	  file = new File(filePath);
	    	    File[] files = file.listFiles();
	    	    for(File file:files){     
	    	
	    	      //System.out.println("��ʾ"+filePath+"��������Ŀ¼"+file.getAbsolutePath()+file.getName()+file.lastModified());
	    	    	//str[i] = file.getName();
	    	    //	str[i] = file.getAbsolutePath();
	    	    	i=i+1;
	    	         
	    	    }
	    	    return i;
	    }
	    /*
	     * �������ļ��������ļ���ʹ��.renameTo()
	     * 
	     */
         public  boolean updateFile(String oldpathname,String newpathname){
        	 source = new File(oldpathname);
             dest = new File(newpathname);
             return source.renameTo(dest);
        	 
         }
	        
	
	
}






	