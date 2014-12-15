import java.io.*;
import java.util.ArrayList;



public class create {
	
	private static ArrayList<String> filelist = new ArrayList<String>();
	
	    // 验证字符串是否为正确路径名的正则表达式
	    private static final String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	    // 通过 sPath.matches(matches) 方法的返回值判断是否正确
	    // sPath 为路径字符串
	   public boolean flag = false;
	    static File file;
	    private static File source ;
		private static File dest;

	    public  boolean DeleteFolder(String deletePath) {// 根据路径删除指定的目录或文件，无论存在与否
	        flag = false;
	        if (deletePath.matches(matches)) {
	            file = new File(deletePath);
	            if (!file.exists()) {// 判断目录或文件是否存在
	                return flag; // 不存在返回 false
	            } else {

	                if (file.isFile()) {// 判断是否为文件
	                    return deleteFile(deletePath);// 为文件时调用删除文件方法
	                } else {
	                    return deleteDirectory(deletePath);// 为目录时调用删除目录方法
	                }
	            }
	        } else {
	         //   System.out.println("要传入正确路径！");
	            return false;
	        }
	    }

	    public boolean deleteFile(String filePath) {// 删除单个文件
	        flag = false;
	        file = new File(filePath);
	        if (file.isFile()) {// 路径为文件且不为空则进行删除
	            file.delete();// 文件删除
	            flag = true;
	        }
	        
	        return flag;
	    }

	    public  boolean deleteDirectory(String dirPath) {// 删除目录（文件夹）以及目录下的文件
	        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
	        if (!dirPath.endsWith(File.separator)) {
	            dirPath = dirPath + File.separator;
	        }
	        File dirFile = new File(dirPath);
	        // 如果dir对应的文件不存在，或者不是一个目录，则退出
	        if (!dirFile.exists() || !dirFile.isDirectory()) {
	            return false;
	        }
	        flag = true;
	        File[] files = dirFile.listFiles();// 获得传入路径下的所有文件
	        for (int i = 0; i < files.length; i++) {// 循环遍历删除文件夹下的所有文件(包括子目录)
	            if (files[i].isFile()) {// 删除子文件
	                flag = deleteFile(files[i].getAbsolutePath());
	              //  System.out.println(files[i].getAbsolutePath() + " 删除成功");
	                if (!flag)
	                    break;// 如果删除失败，则跳出
	            } else {// 运用递归，删除子目录
	                flag = deleteDirectory(files[i].getAbsolutePath());
	                if (!flag)
	                    break;// 如果删除失败，则跳出
	            }
	        }
	        if (!flag)
	            return false;
	        if (dirFile.delete()) {// 删除当前目录
	            return true;
	        } else {
	            return false;
	        }
	    }

	    // 创建单个文件
	    public  boolean createFile(String filePath) {
	        File file = new File(filePath);
	        if (file.exists()) {// 判断文件是否存在
	           // System.out.println("目标文件已存在" + filePath);
	        	
	            return false;
	        }
	        if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
	           // System.out.println("目标文件不能为目录！");
	            return false;
	        }
	        if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
	            // 如果目标文件所在的文件夹不存在，则创建父文件夹 www.2cto.com
	          //  System.out.println("目标文件所在目录不存在，准备创建它！");
	            if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
	               // System.out.println("创建目标文件所在的目录失败！");
	                return false;
	            }
	        }
	        try {
	            if (file.createNewFile()) {// 创建目标文件
	               // System.out.println("创建文件成功:" + filePath);
	                return true;
	            } else {
	               // System.out.println("创建文件失败！");
	                return false;
	            }
	        } catch (IOException e) {// 捕获异常
	            e.printStackTrace();
	           // System.out.println("创建文件失败！" + e.getMessage());
	            return false;
	        }
	    }

	    // 创建目录
	    public  boolean createDir(String destDirName) {
	        File dir = new File(destDirName);
	        if (dir.exists()) {// 判断目录是否存在
	           // System.out.println("创建目录失败，目标目录已存在！");
	            return false;
	        }
	        if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
	            destDirName = destDirName + File.separator;
	        }
	        if (dir.mkdirs()) {// 创建目标目录
	         //   System.out.println("创建目录成功！" + destDirName);
	            return true;
	        } else {
	       //     System.out.println("创建目录失败！");
	            return false;
	        }
	    }
	    
	    
	    
	    
	    /*
	     * 遍历文件夹内的所有文件
	     * 运用递归
	     * 
	     */
	    
	    
	    public String getFilesfile(String filePath){
	    	String str = null;
	    	  file = new File(filePath);
	    	    File[] files = file.listFiles();
	    	    for(File file:files){     
	    	     if(file.isDirectory()){
	    	      /*
	    	       * 递归调用
	    	       */
	    	     getFilesfile(file.getAbsolutePath());
	    	      filelist.add(file.getAbsolutePath());
	    	      //System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath()+file.getName());
	    	      System.out.println(file.getName());
	    	      //str = file.getName();
	    	     }
	    	     else{
	    	     // System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath()+file.getName()+file.lastModified());
	    	      System.out.println(file.getName());
	    	    	//str = file.getName();
	    	     }     
	    	    }
	    	    return str;
	    }
	    
	    
	    
	    
	    /*
	     * 遍历第一层子目录，得到子目录名                              
	     * 遍历子目录，得到子目录下的文件名
	     * 
	     */
	
	    
	 

		public String[] getFiles(String filePath,int i){ 
			
	    	String[] str = new String[i] ;
	    	 i = 0;
	    	  file = new File(filePath);
	    	    File[] files = file.listFiles();
	    	    for(File file:files){     
	    	
	    	      //System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath()+file.getName()+file.lastModified());
	    	    	str[i] = file.getName();
	    	    //	str[i] = file.getAbsolutePath();
	    	    	i=i+1;
	    	         
	    	    }
	    	    return str;
	    }
	    /*
	     * 得到一个目录下文件个数
	     * 
	     * 
	     */
		
		
         public int getFilesint(String filePath){ 
	    	int i = 0;
	    	  file = new File(filePath);
	    	    File[] files = file.listFiles();
	    	    for(File file:files){     
	    	
	    	      //System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath()+file.getName()+file.lastModified());
	    	    	//str[i] = file.getName();
	    	    //	str[i] = file.getAbsolutePath();
	    	    	i=i+1;
	    	         
	    	    }
	    	    return i;
	    }
	    /*
	     * 重命名文件名和子文件名使用.renameTo()
	     * 
	     */
         public  boolean updateFile(String oldpathname,String newpathname){
        	 source = new File(oldpathname);
             dest = new File(newpathname);
             return source.renameTo(dest);
        	 
         }
	        
	
	
}






	