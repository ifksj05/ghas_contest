package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

import message.MessageShow;

public class DbCreate {

	public DbCreate() {
		// TODO Auto-generated constructor stub
		int result = DbManager.db.setData("\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "-- Schema 2023지방_2\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "DROP SCHEMA IF EXISTS `2023지방_2` ;\r\n"
				+ "\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "-- Schema 2023지방_2\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "CREATE SCHEMA IF NOT EXISTS `2023지방_2` DEFAULT CHARACTER SET utf8 ;\r\n"
				+ "USE `2023지방_2` ;\r\n"
				+ "\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "-- Table `2023지방_2`.`division`\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "CREATE TABLE IF NOT EXISTS `2023지방_2`.`division` (\r\n"
				+ "  `d_no` INT(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `d_name` VARCHAR(50) NULL,\r\n"
				+ "  PRIMARY KEY (`d_no`))\r\n"
				+ "ENGINE = InnoDB;\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "-- Table `2023지방_2`.`book`\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "CREATE TABLE IF NOT EXISTS `2023지방_2`.`book` (\r\n"
				+ "  `b_no` INT(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `b_name` VARCHAR(50) NULL,\r\n"
				+ "  `d_no` INT(11) NULL,\r\n"
				+ "  `b_author` VARCHAR(50) NULL,\r\n"
				+ "  `b_count` INT(11) NULL,\r\n"
				+ "  `b_page` INT(11) NULL,\r\n"
				+ "  `b_exp` VARCHAR(500) NULL,\r\n"
				+ "  `b_img` LONGBLOB NULL,\r\n"
				+ "  PRIMARY KEY (`b_no`),\r\n"
				+ "  INDEX `fk_book_division1_idx` (`d_no` ASC) VISIBLE,\r\n"
				+ "  CONSTRAINT `fk_book_division1`\r\n"
				+ "    FOREIGN KEY (`d_no`)\r\n"
				+ "    REFERENCES `2023지방_2`.`division` (`d_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION)\r\n"
				+ "ENGINE = InnoDB;\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "-- Table `2023지방_2`.`user`\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "CREATE TABLE IF NOT EXISTS `2023지방_2`.`user` (\r\n"
				+ "  `u_no` INT(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `u_name` VARCHAR(5) NULL,\r\n"
				+ "  `u_id` VARCHAR(10) NULL,\r\n"
				+ "  `u_pw` VARCHAR(10) NULL,\r\n"
				+ "  PRIMARY KEY (`u_no`))\r\n"
				+ "ENGINE = InnoDB;\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "-- Table `2023지방_2`.`likebook`\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "CREATE TABLE IF NOT EXISTS `2023지방_2`.`likebook` (\r\n"
				+ "  `l_no` INT(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `u_no` INT(11) NULL,\r\n"
				+ "  `b_no` INT(11) NULL,\r\n"
				+ "  PRIMARY KEY (`l_no`),\r\n"
				+ "  INDEX `fk_likebook_book_idx` (`b_no` ASC) VISIBLE,\r\n"
				+ "  INDEX `fk_likebook_user1_idx` (`u_no` ASC) VISIBLE,\r\n"
				+ "  CONSTRAINT `fk_likebook_book`\r\n"
				+ "    FOREIGN KEY (`b_no`)\r\n"
				+ "    REFERENCES `2023지방_2`.`book` (`b_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION,\r\n"
				+ "  CONSTRAINT `fk_likebook_user1`\r\n"
				+ "    FOREIGN KEY (`u_no`)\r\n"
				+ "    REFERENCES `2023지방_2`.`user` (`u_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION)\r\n"
				+ "ENGINE = InnoDB;\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "-- Table `2023지방_2`.`rental`\r\n"
				+ "-- -----------------------------------------------------\r\n"
				+ "CREATE TABLE IF NOT EXISTS `2023지방_2`.`rental` (\r\n"
				+ "  `r_no` INT(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `u_no` INT(11) NULL,\r\n"
				+ "  `b_no` INT(11) NULL,\r\n"
				+ "  `r_date` DATE NULL,\r\n"
				+ "  `r_count` INT(11) NULL,\r\n"
				+ "  `r_reading` INT(11) NULL,\r\n"
				+ "  `r_returnday` DATE NULL,\r\n"
				+ "  PRIMARY KEY (`r_no`),\r\n"
				+ "  INDEX `fk_rental_user1_idx` (`u_no` ASC) VISIBLE,\r\n"
				+ "  INDEX `fk_rental_book1_idx` (`b_no` ASC) VISIBLE,\r\n"
				+ "  CONSTRAINT `fk_rental_user1`\r\n"
				+ "    FOREIGN KEY (`u_no`)\r\n"
				+ "    REFERENCES `2023지방_2`.`user` (`u_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION,\r\n"
				+ "  CONSTRAINT `fk_rental_book1`\r\n"
				+ "    FOREIGN KEY (`b_no`)\r\n"
				+ "    REFERENCES `2023지방_2`.`book` (`b_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION)\r\n"
				+ "ENGINE = InnoDB;\r\n"
				+ ""
				+ "set global local_infile = true;"
				+ ""
				+ "load data local infile './datafiles/user.txt' into table 2023지방_2.user lines terminated by '\\r' ignore 1 lines; \r\n"
				+ "load data local infile './datafiles/division.txt' into table 2023지방_2.division lines terminated by '\\r' ignore 1 lines; \r\n"
				+ "load data local infile './datafiles/book.txt' into table 2023지방_2.book lines terminated by '\\r\\n' ignore 1 lines; \r\n"
				+ "load data local infile './datafiles/likebook.txt' into table 2023지방_2.likebook lines terminated by '\\r' ignore 1 lines; \r\n"
				+ "load data local infile './datafiles/rental.txt' into table 2023지방_2.rental lines terminated by '\\r' ignore 1 lines; \r\n"
				+ ""
				+ "drop user if exists 'user'@'localhost';\r\n"
				+ "create user 'user'@'localhost' identified by '1234';\r\n"
				+ "grant insert, delete, update, select on 2023지방_2.* to 'user'@'localhost';"
				+ ""
				+ "");

		// book에서 \r\n
		// 그 외 테이블은 \r
		
		int result1 = 0;
		
		Vector<Vector<String>> data = DbManager.db.getData("SELECT * FROM 2023지방_2.book;");

		for (Vector<String> row : data) {
			String no = row.get(0);
			
//			System.out.println(no + " " + row.get(1));
			
			File file = new File("./datafiles/book/" + no + ".jpg");
			
			try {
				FileInputStream img = new FileInputStream(file);
				
				DbManager.db.setData("UPDATE `2023지방_2`.`book` SET `b_img` = ? WHERE (`b_no` = ?);", img, no);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result1 = -1;
			}
		}
		
		
		if (result == -1 || result1 == -1) {
			MessageShow.error("셋팅 실패");
			return;
		}
		
		MessageShow.info("셋팅 성공");
	}

}
