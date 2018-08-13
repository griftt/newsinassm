package sinassm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.Query;
import com.entity.User;
import com.poi.ExcelUtil;
import com.serviceImpl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml","classpath:redis/*.xml"})
public class testDrySql {

	@Autowired
	private UserServiceImpl usi;
	
	
	
	@Test
	public void testSelectUserWeiboBuDate(){
		Query q = new Query();
		q.setId(1);
		q.setObjectId(-1);
		usi.s(q);
	}
	@Test
	public void testExportExcel() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Query q = new Query();
		q.setPage(1);
		q.setLimit(100);
		List<User> list = usi.selectPage(q);
		FileOutputStream out =null;
		
		try {
			File file = new File("d://a");
			if(!file.exists()){
				file.getAbsoluteFile().mkdir();
			}
			out = new FileOutputStream("d://a/l3.xls");
					} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 ExcelUtil<User> util = new ExcelUtil<User>();
		  util.exportExcel("first", null, list, out);
		 try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
}
