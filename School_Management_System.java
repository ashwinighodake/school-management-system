/*
 javac filename.java
 java -cp .:mysql-connector-java-8.0.2.jar School_Management_System
*/
import java.lang.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.sql.Driver;
import javax.swing.JPasswordField;

import java.sql.*;
import java.sql.Driver;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Class Name: Admin
// Description : In this class i have designed front end for admin login
// Parent class: JFrame
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Admin extends JFrame
{
    JFrame frame_admin;
    JPanel welcome_panel;
    JLabel welcome_label;
    
    JLabel username_label,password_label,wrong_uname_pass,wrong_pass,wrong_uname;
    JTextField username;
    JPasswordField password;
    JButton submit,previous_admin;
    String uname;
    String pass;

    String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
        
    String DB_URL="jdbc:mysql://localhost/Computer_Department";

 
    String USER ="root";
    String PASS ="";

    Connection conn=null;
    Statement stmt=null;

    Admin()
    {
    
        frame_admin=new JFrame("Admin Login");
        frame_admin.setSize(600,600);
        
        welcome_panel=new JPanel();
        welcome_panel.setBounds(0,0,600,200);
        welcome_panel.setLayout(null);
        welcome_panel.setBackground(Color.BLUE);

        welcome_label=new JLabel();
        welcome_label.setText("Welcome in Admin Login ");
        welcome_label.setBounds(10,90,600,50);
        welcome_label.setForeground(Color.WHITE);
        welcome_label.setFont(new Font("Serif",Font.BOLD,30));
       
        username_label=new JLabel("Username:");
        username_label.setForeground(Color.BLUE);
        username_label.setBounds(30,300,100,30);
        username_label.setFont(new Font("Serif",Font.BOLD,16));

        username=new JTextField();
        username.setForeground(Color.BLUE);
        username.setToolTipText("Enter username");
        username.setBounds(200,300,200,30);
        username.setFont(new Font("Serif",Font.BOLD,15));
       
        password_label=new JLabel("Password:");
        password_label.setForeground(Color.BLUE);
        password_label.setBounds(30,400,100,30);
        password_label.setFont(new Font("Serif",Font.BOLD,16));

        password=new JPasswordField();
        password.setForeground(Color.BLUE);
        password.setToolTipText("Enter your password");
        password.setBounds(200,400,200,30);

        submit=new JButton("Submit");
        submit.setFont(new Font("Serif",Font.BOLD,15));
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200,500,150,30);
        
        previous_admin=new JButton("Previous");
        previous_admin.setText("Previous");
        previous_admin.setFont(new Font("Serif",Font.BOLD,15));
        previous_admin.setBackground(Color.BLUE);
        previous_admin.setForeground(Color.white);
        previous_admin.setBounds(370,500,150,30);

        welcome_panel.add(welcome_label);
        frame_admin.add(welcome_panel);
        frame_admin.add(username_label);
        frame_admin.add(username);
        frame_admin.add(password_label);
        frame_admin.add(password);
        frame_admin.add(submit);
        frame_admin.add(previous_admin);

        frame_admin.setLayout(null);
        frame_admin.setVisible(true);
        frame_admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_admin.getContentPane().setBackground(Color.WHITE) ;
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                uname=username.getText();
                pass=new String(password.getPassword());
                try
                {
                  Class.forName(JDBC_DRIVER).newInstance();
        
                  
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
        
                    
                    stmt=conn.createStatement();
                    String sql;
        
                    sql="Select admin_username,admin_password,admin_name,admin_id from Admin";
                    ResultSet rs=stmt.executeQuery(sql);
        
                    while(rs.next())
                    {
                        String admin_username=rs.getString("admin_username");
                        String admin_password=rs.getString("admin_password");
                        String admin_name=rs.getString("admin_name");
                        int admin_id=rs.getInt("admin_id");

        
                        if((admin_username.equals(uname))&&(admin_password.equals(pass)))
                        {
                            frame_admin.setVisible(false);
                            frame_admin.dispose();
                           Admin_operations adminobj=new Admin_operations(admin_name,admin_id);
                            
                        }
                    }
        
                   
                 }
                 catch(Exception E)
                 {
                    System.out.println(E);
                 }
            }       
        });
        previous_admin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame_admin.setVisible(false);
                frame_admin.dispose();
                School_Management_System sssoj=new School_Management_System();
            }
        });
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// class: Admin Operation
// Description: this class is designed for performin different Admin opertion
// funtion : update_admin,upadte_teacher,update_student,add_tacher,add_student
//           delete_teacher,delete_student
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Admin_operations
{ 
    JFrame frame,frame1,frame2;
    JPanel login_panel,teacher_panel,student_panel;
    JLabel welcome_label,teacher_label,student_label;
    JButton admin_button,teacher_button,student_button,teacher_information,student_information;
    JButton update_admin,add_teacher,update_teacher,delete_teacher,add_student,update_student,delete_student;
    JTextField t_name,s_name;
    JButton submit,previous,previous1,previous2;

    JLabel teacher_id_label,teacher_name_label,teacher_username_label,teacher_password_label,teacher_post_label;
    JLabel teacher_salary_label,teacher_mo_no_label;
    JPanel add_teacher_panel;
    String uname;
    String pass;
    String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
        
    String DB_URL="jdbc:mysql://localhost/Computer_Department";

 
    String USER ="root";
    String PASS ="";

    Connection conn=null;
    PreparedStatement ps=null;
    Statement stmt=null;


    Admin_operations(String admin_name,int admin_id)
    {
        frame=new JFrame("Admin Login");
        frame.setSize(600,600);

        login_panel=new JPanel();
        login_panel.setBounds(0,0,600,200);
        login_panel.setLayout(null);
        login_panel.setBackground(Color.BLUE);

        welcome_label=new JLabel("Welcome : "+admin_name);
        welcome_label.setBounds(10,90,600,50);
        welcome_label.setForeground(Color.WHITE);
        welcome_label.setFont(new Font("Serif",Font.BOLD,30));
        
        admin_button=new JButton("Admin");
        admin_button.setText("Admin");
        admin_button.setFont(new Font("Serif",Font.BOLD,25));
        admin_button.setBounds(0,200,200,50);
        admin_button.setBackground(Color.WHITE);
        admin_button.setForeground(Color.BLUE);

        teacher_button=new JButton("Teacher");
        teacher_button.setText("Teacher");
        teacher_button.setFont(new Font("Serif",Font.BOLD,25));
        teacher_button.setBounds(200,200,200,50);
        teacher_button.setBackground(Color.WHITE);
        teacher_button.setForeground(Color.BLUE);

        student_button=new JButton("Student");
        student_button.setText("Student");
        student_button.setFont(new Font("Serif",Font.BOLD,25));
        student_button.setBounds(400,200,200,50);
        student_button.setBackground(Color.WHITE);
        student_button.setForeground(Color.BLUE);

        update_admin=new JButton("Update Your info");
        update_admin.setText("Update Your info");
        update_admin.setFont(new Font("Serif",Font.BOLD,15));
        update_admin.setBounds(0,250,200,50);
        update_admin.setBackground(Color.BLUE);
        update_admin.setForeground(Color.WHITE);
        
        teacher_information=new JButton("Teacher info");
        teacher_information.setText("Teacher info");
        teacher_information.setFont(new Font("Serif",Font.BOLD,15));
        teacher_information.setBounds(0,300,200,50);
        teacher_information.setBackground(Color.BLUE);
        teacher_information.setForeground(Color.WHITE);
        
        student_information=new JButton("Student info");
        student_information.setText("Student info");
        student_information.setFont(new Font("Serif",Font.BOLD,15));
        student_information.setBounds(0,350,200,50);
        student_information.setBackground(Color.BLUE);
        student_information.setForeground(Color.WHITE);
        
        add_teacher=new JButton("Add");
        add_teacher.setText("Add");
        add_teacher.setFont(new Font("Serif",Font.BOLD,15));
        add_teacher.setBounds(200,250,200,50);
        add_teacher.setBackground(Color.BLUE);
        add_teacher.setForeground(Color.WHITE);
        
        update_teacher=new JButton("Update");
        update_teacher.setText("Update");
        update_teacher.setFont(new Font("Serif",Font.BOLD,15));
        update_teacher.setBounds(200,300,200,50);
        update_teacher.setBackground(Color.BLUE);
        update_teacher.setForeground(Color.WHITE);
        
        delete_teacher=new JButton("Delete");
        delete_teacher.setText("Delete");
        delete_teacher.setFont(new Font("Serif",Font.BOLD,15));
        delete_teacher.setBounds(200,350,200,50);
        delete_teacher.setBackground(Color.BLUE);
        delete_teacher.setForeground(Color.WHITE);
        
        add_student=new JButton("Add");
        add_student.setText("Add");
        add_student.setFont(new Font("Serif",Font.BOLD,15));
        add_student.setBounds(400,250,200,50);
        add_student.setBackground(Color.BLUE);
        add_student.setForeground(Color.WHITE);
        
        update_student=new JButton("Update");
        update_student.setText("Update");
        update_student.setFont(new Font("Serif",Font.BOLD,15));
        update_student.setBounds(400,300,200,50);
        update_student.setBackground(Color.BLUE);
        update_student.setForeground(Color.WHITE);
        
        delete_student=new JButton("Delete");
        delete_student.setText("Delete");
        delete_student.setFont(new Font("Serif",Font.BOLD,15));
        delete_student.setBounds(400,350,200,50);
        delete_student.setBackground(Color.BLUE);
        delete_student.setForeground(Color.WHITE);

        previous=new JButton("Previous");
        previous.setText("Previous");
        previous.setFont(new Font("Serif",Font.BOLD,15));
        previous.setBounds(450,520,130,50);
        previous.setBackground(Color.BLUE);
        previous.setForeground(Color.white);
        
        login_panel.add(welcome_label);
        frame.add(login_panel);
        frame.add(admin_button);
        frame.add(teacher_button);
        frame.add(student_button);
        frame.add(update_admin);
        frame.add(teacher_information);
        frame.add(student_information);
        frame.add(add_teacher);
        frame.add(update_teacher);
        frame.add(delete_teacher);
        frame.add(add_student);
        frame.add(update_student);
        frame.add(delete_student);
        frame.add(previous);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE) ;
        
        update_admin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.setVisible(false);
                frame.dispose();
                
                Update_Admin(admin_name,admin_id);
            }
        });
        teacher_information.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                frame.dispose();
                Print_Teacher_information(admin_name,admin_id);         
            }       
        });
        
        student_information.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                Print_Student_Information(admin_name,admin_id);        
            }       
        });
        add_teacher.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               frame.setVisible(false);
               Add_Teacher(admin_name,admin_id);
           }
        });
        add_student.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                Add_Student(admin_name,admin_id);
            }
        });
        update_teacher.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
              frame.setVisible(false);
              Update_Teacher(admin_name,admin_id);
           }
        });
        update_student.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
              frame.setVisible(false);
              Update_Student(admin_name,admin_id); 
           }
        });
        delete_teacher.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
              frame.setVisible(false);
              Delete_Teacher(admin_name,admin_id);
           }
        });
        delete_student.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                Delete_Student(admin_name,admin_id);              
            }
        });
        previous.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                frame.dispose();
                Admin obj=new Admin();
            }
        });
    }
    public void Update_Admin(String admin_name,int admin_id)
    {
        JPanel update_admin_panel;
        JLabel update_admin_label,input_label,admin_name_label,admin_username_label,admin_password_label;
        JLabel admin_post_label,admin_mobile_label;
      
        
        frame1=new JFrame("Update Admin");

        update_admin_panel=new JPanel();
        update_admin_panel.setLayout(null);
        update_admin_panel.setBounds(0,0,700,200);
        update_admin_panel.setBackground(Color.BLUE);
      
        update_admin_label=new JLabel();
        update_admin_label.setText("Update Admin");
        update_admin_label.setBounds(150,90,300,50);
        update_admin_label.setFont(new Font("Serif",Font.BOLD,30));
        update_admin_label.setForeground(Color.WHITE);

        input_label=new JLabel();
        input_label.setText("Click on what do you want to update:");
        input_label.setBounds(10,210,700,30);
        input_label.setFont(new Font("Serif",Font.BOLD,25));
        input_label.setForeground(Color.BLUE);
 
        admin_name_label=new JLabel();
        admin_name_label.setText("* Update Admin name");
        admin_name_label.setBounds(10,250,700,30);
        admin_name_label.setFont(new Font("Serif",Font.BOLD,25));
        admin_name_label.setForeground(Color.BLUE.darker());
        admin_name_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        admin_username_label=new JLabel();
        admin_username_label.setText("* Update Admin username");
        admin_username_label.setBounds(10,300,700,30);
        admin_username_label.setFont(new Font("Serif",Font.BOLD,25));
        admin_username_label.setForeground(Color.BLUE.darker());
        admin_username_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        admin_password_label=new JLabel();
        admin_password_label.setText("* Update Admin password");
        admin_password_label.setBounds(10,350,700,30);
        admin_password_label.setFont(new Font("Serif",Font.BOLD,25));
        admin_password_label.setForeground(Color.BLUE.darker());
        admin_password_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        admin_post_label=new JLabel();
        admin_post_label.setText("* Update Admin Post");
        admin_post_label.setBounds(10,400,700,30);
        admin_post_label.setFont(new Font("Serif",Font.BOLD,25));
        admin_post_label.setForeground(Color.BLUE.darker());
        admin_post_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        admin_mobile_label=new JLabel();
        admin_mobile_label.setText("* Update admin mobile number");
        admin_mobile_label.setBounds(10,450,700,30);
        admin_mobile_label.setFont(new Font("Serif",Font.BOLD,25));
        admin_mobile_label.setForeground(Color.BLUE.darker());
        admin_mobile_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        previous2=new JButton("Previous");
        previous2.setText("Previous");
        previous2.setFont(new Font("Serif",Font.BOLD,15));
        previous2.setBounds(450,500,150,30);
        previous2.setForeground(Color.white);
        previous2.setBackground(Color.blue);
       
        update_admin_panel.add(update_admin_label);
        frame1.add(update_admin_panel);
        frame1.add(admin_name_label);
        frame1.add(input_label);
        frame1.add(admin_username_label);
        frame1.add(admin_password_label);
        frame1.add(admin_post_label);
        frame1.add(admin_mobile_label);
        frame1.add(previous2);

        frame1.setSize(700,700);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.WHITE) ;
        
        admin_name_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
              frame1.setVisible(false);

              frame2=new JFrame("Admin Name Update");
      
              JPanel admin_name_panel=new JPanel();
              admin_name_panel.setBounds(0,0,700,200);
              admin_name_panel.setBackground(Color.blue);
              admin_name_panel.setLayout(null);
              
              JLabel update_name_label=new JLabel("Update admin new name");
              update_name_label.setForeground(Color.WHITE);
              update_name_label.setFont(new Font("Serif",Font.BOLD,30));
              update_name_label.setBounds(10,90,650,50);

              JLabel admin_new_name_label=new JLabel("Enter admin new name:");
              admin_new_name_label.setBounds(10,250,300,30);
              admin_new_name_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_new_name_label.setForeground(Color.BLUE);

              JTextField admin_name_TF=new JTextField();
              admin_name_TF.setToolTipText("Enter admin new name");
              admin_name_TF.setForeground(Color.blue);
              admin_name_TF.setBounds(320,250,300,30);
              admin_name_TF.setFont(new Font("Serif",Font.BOLD,20));

              JLabel admin_id_label=new JLabel("Enter Admin id:");
              admin_id_label.setForeground(Color.BLUE);
              admin_id_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_label.setBounds(10,300,300,30);

              JTextField admin_id_TF=new JTextField();
              admin_id_TF.setToolTipText("Enter id of admin which you want to update name");
              admin_id_TF.setForeground(Color.BLUE);
              admin_id_TF.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_TF.setBounds(320,300,300,30);
              
              JButton Submit=new JButton("Submit");
              Submit.setBackground(Color.BLUE);
              Submit.setForeground(Color.white);
              Submit.setBounds(100,400,150,30);
              Submit.setFont(new Font("Serif",Font.BOLD,15));

              JButton previous3=new JButton("Previous");
              previous3.setBackground(Color.BLUE);
              previous3.setForeground(Color.WHITE);
              previous3.setBounds(300,400,150,30);
              previous3.setFont(new Font("Serif",Font.BOLD,15));

              admin_name_panel.add(update_name_label);
              frame2.add(admin_name_panel);
              frame2.add(admin_new_name_label);
              frame2.add(admin_id_label);
              frame2.add(admin_name_TF);
              frame2.add(admin_id_TF);
              frame2.add(Submit);
              frame2.add(previous3);
              frame2.setSize(700,600);
              frame2.setLayout(null);
              frame2.setVisible(true);
              frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame2.getContentPane().setBackground(Color.WHITE) ;        
      
              previous3.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Admin(admin_name,admin_id);
                  }
              });
              Submit.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                     String a_name=admin_name_TF.getText();
                     int a_id=Integer.parseInt(admin_id_TF.getText());
                     try
                     {
                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Admin set admin_name='%s' where admin_id=%d",a_name,a_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                        }
      
                    }
                     catch(Exception e)
                     {
                         System.out.println(e);
                     }
                  }
              });
            }
           
        });
        admin_username_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
              frame1.setVisible(false);

              frame2=new JFrame("Admin Userame Update");
      
              JPanel admin_uname_panel=new JPanel();
              admin_uname_panel.setBounds(0,0,700,200);
              admin_uname_panel.setBackground(Color.blue);
              admin_uname_panel.setLayout(null);
              
              JLabel update_uname_label=new JLabel("Update admin new username");
              update_uname_label.setForeground(Color.WHITE);
              update_uname_label.setFont(new Font("Serif",Font.BOLD,30));
              update_uname_label.setBounds(10,90,650,50);

              JLabel admin_new_uname_label=new JLabel("Enter admin new username:");
              admin_new_uname_label.setBounds(10,250,350,30);
              admin_new_uname_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_new_uname_label.setForeground(Color.BLUE);

              JTextField admin_uname_TF=new JTextField();
              admin_uname_TF.setToolTipText("Enter admin new username");
              admin_uname_TF.setForeground(Color.blue);
              admin_uname_TF.setBounds(370,250,300,30);
              admin_uname_TF.setFont(new Font("Serif",Font.BOLD,20));

              JLabel admin_id_label=new JLabel("Enter Admin id:");
              admin_id_label.setForeground(Color.BLUE);
              admin_id_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_label.setBounds(10,300,300,30);

              JTextField admin_id_TF=new JTextField();
              admin_id_TF.setToolTipText("Enter id of admin which you want to update username");
              admin_id_TF.setForeground(Color.BLUE);
              admin_id_TF.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_TF.setBounds(370,300,300,30);
              
              JButton Submit=new JButton("Submit");
              Submit.setBackground(Color.BLUE);
              Submit.setForeground(Color.white);
              Submit.setBounds(100,400,150,30);
              Submit.setFont(new Font("Serif",Font.BOLD,15));

              JButton previous3=new JButton("Previous");
              previous3.setBackground(Color.BLUE);
              previous3.setForeground(Color.WHITE);
              previous3.setBounds(300,400,150,30);
              previous3.setFont(new Font("Serif",Font.BOLD,15));

              admin_uname_panel.add(update_uname_label);
              frame2.add(admin_uname_panel);
              frame2.add(admin_new_uname_label);
              frame2.add(admin_id_label);
              frame2.add(admin_uname_TF);
              frame2.add(admin_id_TF);
              frame2.add(Submit);
              frame2.add(previous3);
              frame2.setSize(700,600);
              frame2.setLayout(null);
              frame2.setVisible(true);
              frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame2.getContentPane().setBackground(Color.WHITE) ;        
      
              previous3.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Admin(admin_name,admin_id);
                  }
              });
              Submit.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                     String a_name=admin_uname_TF.getText();
                     int a_id=Integer.parseInt(admin_id_TF.getText());
                     try
                     {
                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Admin set admin_username='%s' where admin_id=%d",a_name,a_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                        }
      
                    }
                     catch(Exception e)
                     {
                         System.out.println(e);
                     }
                  }
              });
            }
           
        });
        admin_password_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
              frame1.setVisible(false);

              frame2=new JFrame("Admin Password Update");
      
              JPanel admin_password_panel=new JPanel();
              admin_password_panel.setBounds(0,0,700,200);
              admin_password_panel.setBackground(Color.blue);
              admin_password_panel.setLayout(null);
              
              JLabel update_password_label=new JLabel("Update admin new password");
              update_password_label.setForeground(Color.WHITE);
              update_password_label.setFont(new Font("Serif",Font.BOLD,30));
              update_password_label.setBounds(10,90,650,50);

              JLabel admin_new_password_label=new JLabel("Enter admin new password:");
              admin_new_password_label.setBounds(10,250,350,30);
              admin_new_password_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_new_password_label.setForeground(Color.BLUE);

              JTextField admin_password_TF=new JTextField();
              admin_password_TF.setToolTipText("Enter admin new password");
              admin_password_TF.setForeground(Color.blue);
              admin_password_TF.setBounds(370,250,300,30);
              admin_password_TF.setFont(new Font("Serif",Font.BOLD,20));

              JLabel admin_id_label=new JLabel("Enter Admin id:");
              admin_id_label.setForeground(Color.BLUE);
              admin_id_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_label.setBounds(10,300,300,30);

              JTextField admin_id_TF=new JTextField();
              admin_id_TF.setToolTipText("Enter id of admin which you want to update password");
              admin_id_TF.setForeground(Color.BLUE);
              admin_id_TF.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_TF.setBounds(370,300,300,30);
              
              JButton Submit=new JButton("Submit");
              Submit.setBackground(Color.BLUE);
              Submit.setForeground(Color.white);
              Submit.setBounds(100,400,150,30);
              Submit.setFont(new Font("Serif",Font.BOLD,15));

              JButton previous3=new JButton("Previous");
              previous3.setBackground(Color.BLUE);
              previous3.setForeground(Color.WHITE);
              previous3.setBounds(300,400,150,30);
              previous3.setFont(new Font("Serif",Font.BOLD,15));

              admin_password_panel.add(update_password_label);
              frame2.add(admin_password_panel);
              frame2.add(admin_new_password_label);
              frame2.add(admin_id_label);
              frame2.add(admin_password_TF);
              frame2.add(admin_id_TF);
              frame2.add(Submit);
              frame2.add(previous3);
              frame2.setSize(700,600);
              frame2.setLayout(null);
              frame2.setVisible(true);
              frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame2.getContentPane().setBackground(Color.WHITE) ;        
      
              previous3.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Admin(admin_name,admin_id);
                  }
              });
              Submit.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                     String a_pass=admin_password_TF.getText();
                     int a_id=Integer.parseInt(admin_id_TF.getText());
                     try
                     {
                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Admin set admin_password='%s' where admin_id=%d",a_pass,a_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                        }
      
                    }
                     catch(Exception e)
                     {
                         System.out.println(e);
                     }
                  }
              });
            }
           
        });
        admin_post_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
              frame1.setVisible(false);

              frame2=new JFrame("Admin Post Update");
      
              JPanel admin_post_panel=new JPanel();
              admin_post_panel.setBounds(0,0,700,200);
              admin_post_panel.setBackground(Color.blue);
              admin_post_panel.setLayout(null);
              
              JLabel update_post_label=new JLabel("Update admin new post");
              update_post_label.setForeground(Color.WHITE);
              update_post_label.setFont(new Font("Serif",Font.BOLD,30));
              update_post_label.setBounds(10,90,650,50);

              JLabel admin_new_post_label=new JLabel("Enter admin new post:");
              admin_new_post_label.setBounds(10,250,350,30);
              admin_new_post_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_new_post_label.setForeground(Color.BLUE);

              JTextField admin_post_TF=new JTextField();
              admin_post_TF.setToolTipText("Enter admin new post");
              admin_post_TF.setForeground(Color.blue);
              admin_post_TF.setBounds(370,250,300,30);
              admin_post_TF.setFont(new Font("Serif",Font.BOLD,20));

              JLabel admin_id_label=new JLabel("Enter Admin id:");
              admin_id_label.setForeground(Color.BLUE);
              admin_id_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_label.setBounds(10,300,300,30);

              JTextField admin_id_TF=new JTextField();
              admin_id_TF.setToolTipText("Enter id of admin which you want to update post");
              admin_id_TF.setForeground(Color.BLUE);
              admin_id_TF.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_TF.setBounds(370,300,300,30);
              
              JButton Submit=new JButton("Submit");
              Submit.setBackground(Color.BLUE);
              Submit.setForeground(Color.white);
              Submit.setBounds(100,400,150,30);
              Submit.setFont(new Font("Serif",Font.BOLD,15));

              JButton previous3=new JButton("Previous");
              previous3.setBackground(Color.BLUE);
              previous3.setForeground(Color.WHITE);
              previous3.setBounds(300,400,150,30);
              previous3.setFont(new Font("Serif",Font.BOLD,15));

              admin_post_panel.add(update_post_label);
              frame2.add(admin_post_panel);
              frame2.add(admin_new_post_label);
              frame2.add(admin_id_label);
              frame2.add(admin_post_TF);
              frame2.add(admin_id_TF);
              frame2.add(Submit);
              frame2.add(previous3);
              frame2.setSize(700,600);
              frame2.setLayout(null);
              frame2.setVisible(true);
              frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame2.getContentPane().setBackground(Color.WHITE) ;        
      
              previous3.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Admin(admin_name,admin_id);
                  }
              });
              Submit.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                     String a_post=admin_post_TF.getText();
                     int a_id=Integer.parseInt(admin_id_TF.getText());
                     try
                     {
                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Admin set admin_post='%s' where admin_id=%d",a_post,a_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                        }
      
                    }
                     catch(Exception e)
                     {
                         System.out.println(e);
                     }
                  }
              });
            }
           
        });
        admin_mobile_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
              frame1.setVisible(false);

              frame2=new JFrame("Admin Mobile number Update");
      
              JPanel admin_mobile_panel=new JPanel();
              admin_mobile_panel.setBounds(0,0,700,200);
              admin_mobile_panel.setBackground(Color.blue);
              admin_mobile_panel.setLayout(null);
              
              JLabel update_mobile_label=new JLabel("Update admin new Mobile number");
              update_mobile_label.setForeground(Color.WHITE);
              update_mobile_label.setFont(new Font("Serif",Font.BOLD,30));
              update_mobile_label.setBounds(10,90,650,50);

              JLabel admin_new_mobile_label=new JLabel("Enter admin new mobile number:");
              admin_new_mobile_label.setBounds(10,250,400,30);
              admin_new_mobile_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_new_mobile_label.setForeground(Color.BLUE);

              JTextField admin_mobile_TF=new JTextField();
              admin_mobile_TF.setToolTipText("Enter admin new mobile number");
              admin_mobile_TF.setForeground(Color.blue);
              admin_mobile_TF.setBounds(410,250,250,30);
              admin_mobile_TF.setFont(new Font("Serif",Font.BOLD,20));

              JLabel admin_id_label=new JLabel("Enter Admin id:");
              admin_id_label.setForeground(Color.BLUE);
              admin_id_label.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_label.setBounds(10,300,300,30);

              JTextField admin_id_TF=new JTextField();
              admin_id_TF.setToolTipText("Enter id of admin which you want to update mobile number");
              admin_id_TF.setForeground(Color.BLUE);
              admin_id_TF.setFont(new Font("Serif",Font.BOLD,20));
              admin_id_TF.setBounds(410,300,250,30);
              
              JButton Submit=new JButton("Submit");
              Submit.setBackground(Color.BLUE);
              Submit.setForeground(Color.white);
              Submit.setBounds(100,400,150,30);
              Submit.setFont(new Font("Serif",Font.BOLD,15));

              JButton previous3=new JButton("Previous");
              previous3.setBackground(Color.BLUE);
              previous3.setForeground(Color.WHITE);
              previous3.setBounds(300,400,150,30);
              previous3.setFont(new Font("Serif",Font.BOLD,15));

              admin_mobile_panel.add(update_mobile_label);
              frame2.add(admin_mobile_panel);
              frame2.add(admin_new_mobile_label);
              frame2.add(admin_id_label);
              frame2.add(admin_mobile_TF);
              frame2.add(admin_id_TF);
              frame2.add(Submit);
              frame2.add(previous3);
              frame2.setSize(700,600);
              frame2.setLayout(null);
              frame2.setVisible(true);
              frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame2.getContentPane().setBackground(Color.WHITE) ;        
      
              previous3.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Admin(admin_name,admin_id);
                  }
              });
              Submit.addActionListener(new ActionListener()
              {
                  public void actionPerformed(ActionEvent ae)
                  {
                     String a_mobile=admin_mobile_TF.getText();
                     int a_id=Integer.parseInt(admin_id_TF.getText());
                     try
                     {
                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Admin set admin_mobile_no='%s' where admin_id=%d",a_mobile,a_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                        }
      
                    }
                     catch(Exception e)
                     {
                         System.out.println(e);
                     }
                  }
              });
            }
           
        });
        
        previous2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame1.setVisible(false);
                frame1.dispose();
                Admin_operations aobj=new Admin_operations(admin_name,admin_id);               
            }
        });

 
    }
    public void Print_Teacher_information(String admin_name,int admin_id)
    {
        frame1=new JFrame("Teacher information");

        teacher_panel=new JPanel();
        teacher_panel.setBounds(0,0,700,100);
        teacher_panel.setBackground(Color.BLUE);
        teacher_panel.setLayout(null);

        teacher_label=new JLabel("Enter teacher name whose information you want to see:");
        teacher_label.setBounds(10,30,700,50);
        teacher_label.setForeground(Color.WHITE);
        teacher_label.setFont(new Font("Serif",Font.BOLD,20));

        t_name=new JTextField();
        t_name.setBounds(50,170,310,40);
        t_name.setToolTipText("Enter Teacher name here");
        t_name.setForeground(Color.BLUE);
        t_name.setFont(new Font("Serif",Font.BOLD,20));

        submit=new JButton();
        submit.setText("Submit");
        submit.setFont(new Font("Serif",Font.BOLD,20));
        submit.setBounds(50,260,150,40);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLUE);
        
        previous1=new JButton("Previous");
        previous1.setText("Previous");
        previous1.setFont(new Font("Serif",Font.BOLD,20));
        previous1.setBounds(300,260,150,40);
        previous1.setForeground(Color.white);
        previous1.setBackground(Color.blue);
        
        frame1.add(teacher_panel);
        teacher_panel.add(teacher_label);
        
        frame1.add(t_name);
        frame1.add(submit);
        frame1.add(previous1);
        frame1.setSize(700,400);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.WHITE) ;
    
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String tname=t_name.getText();
                try
                {
                    Class.forName(JDBC_DRIVER).newInstance();
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
                    stmt=conn.createStatement();
                    String sql;
                    sql="select teacher_id,teacher_name,teacher_post,teacher_salary,teacher_username,teacher_password,teacher_mobile_no from Teacher";
                    ResultSet rs=stmt.executeQuery(sql);
            
                    while(rs.next())
                    {
                        String teacher_name=rs.getString("teacher_name");
                        int teacher_id=rs.getInt("teacher_id");
                        if(tname.equals(teacher_name))
                        {
                            frame1.setVisible(false);
                            String teacher_post=rs.getString("teacher_post");
                            String teacher_salary=rs.getString("teacher_salary");
                            String teacher_username=rs.getString("teacher_username");
                            String teacher_password=rs.getString("teacher_password");
                            int teacher_mobile_no=rs.getInt("teacher_mobile_no");
                            
                            Teacher_information teacher_obj=new Teacher_information(teacher_id,teacher_name,teacher_post,teacher_salary,teacher_username,teacher_password,teacher_mobile_no);
                            
                        }
                    }
                 }
                catch(Exception E)
                {
                    System.out.println(E);
                }
            }       
        });
        previous1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame1.setVisible(false);
                frame1.dispose();
                
                Admin_operations aobj=new Admin_operations(admin_name,admin_id);
            } 
        });

    }
    public void Print_Student_Information(String admin_name,int admin_id)
    {
        frame2=new JFrame("Student information");

        student_panel=new JPanel();
        student_panel.setBounds(0,0,700,100);
        student_panel.setBackground(Color.BLUE);
        student_panel.setLayout(null);
        
        student_label=new JLabel("Enter Student name here:");
        student_label.setFont(new Font("Serif",Font.BOLD,20));
        student_label.setForeground(Color.WHITE);
        student_label.setBounds(10,40,700,50);

        s_name=new JTextField();
        s_name.setBounds(50,170,300,40);
        s_name.setForeground(Color.BLUE);
        s_name.setFont(new Font("Serif",Font.BOLD,20));
        s_name.setToolTipText("Enter student name here");
        
        submit=new JButton("Submit");
        submit.setText("Submit");
        submit.setFont(new Font("Serif",Font.BOLD,20));
        submit.setBounds(50,260,150,40);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLUE);

        submit=new JButton("Submit");
        submit.setText("Submit");
        submit.setFont(new Font("Serif",Font.BOLD,15));
        submit.setBounds(50,260,150,40);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLUE);

        previous2=new JButton("Previous");
        previous2.setText("Previous");
        previous2.setFont(new Font("Serif",Font.BOLD,15));
        previous2.setBounds(300,260,150,40);
        previous2.setForeground(Color.white);
        previous2.setBackground(Color.blue);
        
        student_panel.add(student_label);
        frame2.add(student_panel);
        frame2.add(s_name);
        frame2.add(submit);
        frame2.add(previous2);

        frame2.setSize(700,400);
        frame2.setLayout(null);  
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().setBackground(Color.WHITE) ;
    
        submit.addActionListener(new ActionListener()
         {   public void actionPerformed(ActionEvent e)
            {
                String sname=s_name.getText();
                try
                {
                    Class.forName(JDBC_DRIVER).newInstance();
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);    
                    stmt=conn.createStatement();
                    String sql;
            
                    sql="select Rollno,student_name,student_class,student_div,student_username,student_password from Student";
                    ResultSet rs=stmt.executeQuery(sql);
            
                    while(rs.next())
                    {
                        String student_name=rs.getString("student_name");
                        int student_id=rs.getInt("Rollno");
                        if(sname.equals(student_name))
                        {
                            frame2.setVisible(false);
                            String student_class=rs.getString("student_class");
                            String student_div=rs.getString("student_div");
                            String student_username=rs.getString("student_username");
                            String student_password=rs.getString("student_password");
                            
                            Student_information student_obj=new Student_information(student_id,student_name,student_class,student_div,student_username,student_password);
                            
                            }
                        }                 
                 }
                catch(Exception E)
                {
                    System.out.println(E);
                }
        
            }
        });
        previous2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame2.setVisible(false);
                frame2.dispose();
                Admin_operations aobj=new Admin_operations(admin_name,admin_id);               
            }
        });

    }
    public void Add_Teacher(String admin_name,int admin_id)
    {
        JLabel add_teacher_label;
        JTextField teacher_id_text,teacher_name_text,teacher_username_text;
        JTextField teacher_password_text,teacher_post_text,teacher_salary_text,teacher_mo_no_text;
        JButton submit;
       
       frame1=new JFrame("Add Teacher");

       add_teacher_panel=new JPanel();
       add_teacher_panel.setLayout(null);
       add_teacher_panel.setBounds(0,0,800,200);
       add_teacher_panel.setBackground(Color.BLUE);
      
       add_teacher_label=new JLabel();
       add_teacher_label.setText("Add Teacher");
       add_teacher_label.setBounds(150,90,300,50);
       add_teacher_label.setFont(new Font("Serif",Font.BOLD,30));
       add_teacher_label.setForeground(Color.WHITE);

       teacher_id_label=new JLabel();
       teacher_id_label.setText("Enter Teacher id:");
       teacher_id_label.setFont(new Font("Serif",Font.BOLD,20));
       teacher_id_label.setForeground(Color.BLUE);
       teacher_id_label.setBounds(10,250,300,30);

       teacher_id_text=new JTextField();
       teacher_id_text.setToolTipText("Enter Teacher id:");
       teacher_id_text.setFont(new Font("Serif",Font.BOLD,20));
       teacher_id_text.setForeground(Color.BLUE);
       teacher_id_text.setBounds(320,250,300,30);

       teacher_name_label=new JLabel();
       teacher_name_label.setText("Enter Teacher name:");
       teacher_name_label.setFont(new Font("Serif",Font.BOLD,20));
       teacher_name_label.setForeground(Color.BLUE);
       teacher_name_label.setBounds(10,300,300,30);
 
       teacher_name_text=new JTextField();
       teacher_name_text.setToolTipText("Enter Teacher name:");
       teacher_name_text.setFont(new Font("Serif",Font.BOLD,20));
       teacher_name_text.setForeground(Color.BLUE);
       teacher_name_text.setBounds(320,300,300,30);
 
       teacher_username_label=new JLabel();
       teacher_username_label.setText("Enter Teacher username:");
       teacher_username_label.setFont(new Font("Serif",Font.BOLD,20));
       teacher_username_label.setForeground(Color.BLUE);
       teacher_username_label.setBounds(10,350,300,30);
 
       teacher_username_text=new JTextField();
       teacher_username_text.setToolTipText("Enter Teacher username:");
       teacher_username_text.setFont(new Font("Serif",Font.BOLD,20));
       teacher_username_text.setForeground(Color.BLUE);
       teacher_username_text.setBounds(320,350,300,30);
 
       teacher_password_label=new JLabel();
       teacher_password_label.setText("Enter Teacher password:");
       teacher_password_label.setFont(new Font("Serif",Font.BOLD,20));
       teacher_password_label.setForeground(Color.BLUE);
       teacher_password_label.setBounds(10,400,300,30);

       teacher_password_text=new JTextField();
       teacher_password_text.setToolTipText("Enter Teacher password:");
       teacher_password_text.setFont(new Font("Serif",Font.BOLD,20));
       teacher_password_text.setForeground(Color.BLUE);
       teacher_password_text.setBounds(320,400,300,30);

       teacher_post_label=new JLabel();
       teacher_post_label.setText("Enter Teacher post:");
       teacher_post_label.setFont(new Font("Serif",Font.BOLD,20));
       teacher_post_label.setForeground(Color.BLUE);
       teacher_post_label.setBounds(10,450,300,30);

       teacher_post_text=new JTextField();
       teacher_post_text.setToolTipText("Enter Teacher post:");
       teacher_post_text.setFont(new Font("Serif",Font.BOLD,20));
       teacher_post_text.setForeground(Color.BLUE);
       teacher_post_text.setBounds(320,450,300,30);

       teacher_salary_label=new JLabel();
       teacher_salary_label.setText("Enter Teacher salary:");
       teacher_salary_label.setFont(new Font("Serif",Font.BOLD,20));
       teacher_salary_label.setForeground(Color.BLUE);
       teacher_salary_label.setBounds(10,500,300,30);

       teacher_salary_text=new JTextField();
       teacher_salary_text.setToolTipText("Enter Teacher salary:");
       teacher_salary_text.setFont(new Font("Serif",Font.BOLD,20));
       teacher_salary_text.setForeground(Color.BLUE);
       teacher_salary_text.setBounds(320,500,300,30);

       teacher_mo_no_label=new JLabel();
       teacher_mo_no_label.setText("Enter Teacher mobile_no:");
       teacher_mo_no_label.setFont(new Font("Serif",Font.BOLD,20));
       teacher_mo_no_label.setForeground(Color.BLUE);
       teacher_mo_no_label.setBounds(10,550,300,30);

       teacher_mo_no_text=new JTextField();
       teacher_mo_no_text.setToolTipText("Enter Teacher mobile_no:");
       teacher_mo_no_text.setFont(new Font("Serif",Font.BOLD,20));
       teacher_mo_no_text.setForeground(Color.BLUE);
       teacher_mo_no_text.setBounds(320,550,300,30);

       submit=new JButton();
       submit.setText("Submit");
       submit.setFont(new Font("Serif",Font.BOLD,15));
       submit.setForeground(Color.WHITE);
       submit.setBackground(Color.BLUE);
       submit.setBounds(100,600,150,40);
       
       previous2=new JButton("Previous");
       previous2.setText("Previous");
       previous2.setFont(new Font("Serif",Font.BOLD,15));
       previous2.setBounds(300,600,150,40);
       previous2.setForeground(Color.white);
       previous2.setBackground(Color.blue);
       
       add_teacher_panel.add(add_teacher_label);
       frame1.add(add_teacher_panel);
       frame1.add(teacher_id_label);
       frame1.add(teacher_id_text);
       frame1.add(teacher_name_label);
       frame1.add(teacher_name_text);
       frame1.add(teacher_username_label);
       frame1.add(teacher_password_label);
       frame1.add(teacher_username_text);
       frame1.add(teacher_password_text);
       frame1.add(teacher_post_label);
       frame1.add(teacher_post_text);
       frame1.add(teacher_salary_label);
       frame1.add(teacher_salary_text);
       frame1.add(teacher_mo_no_label);
       frame1.add(teacher_mo_no_text);
        frame1.add(submit);
        frame1.add(previous2);

       frame1.setSize(800,900);
       frame1.setLayout(null);
       frame1.setVisible(true);
       frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame1.getContentPane().setBackground(Color.WHITE) ;

       submit.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
            
            int id=Integer.parseInt(teacher_id_text.getText());
            String name=teacher_name_text.getText();
            String username=teacher_username_text.getText();
            String password=teacher_password_text.getText();
            String post=teacher_post_text.getText();
            int salary=Integer.parseInt(teacher_salary_text.getText());
            int mo_no=Integer.parseInt(teacher_mo_no_text.getText());
            try
            {
              Class.forName(JDBC_DRIVER).newInstance();
              conn=DriverManager.getConnection(DB_URL,USER,PASS);
              stmt=conn.createStatement();
              String sql;
               ResultSet rs=null;

                sql=String.format("Insert into Teacher values (%d,'%s','%s','%s','%s',%d,%d,%d)",id,name,username,password,post,salary,mo_no,admin_id);
                ps=conn.prepareStatement(sql);
                int count=ps.executeUpdate(sql);
                if(count>0)
                {
                    frame1.setVisible(false);
                
                    JLabel success_label=new JLabel();
                    success_label.setText("Inserted Successfully");
                    success_label.setFont(new Font("Serif",Font.BOLD,25));
                    success_label.setForeground(Color.BLUE);
                    success_label.setBounds(100,650,500,30);
                    
                    frame1.add(success_label);
                    frame1.setVisible(true);
                } //if close
    
             }//try close
             catch(Exception E)
             {
                System.out.println(E);
             }//catch close

           }//actionperformed close
       });//submit clos

       previous2.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent ae)
           {
            frame1.setVisible(false);
            frame1.dispose();
            Admin_operations aobj=new Admin_operations(admin_name,admin_id); 
           }
        });
    }//function close
    public void Add_Student(String admin_name,int admin_id)
    {
        JPanel add_student_panel;
        JLabel add_student_label;
        JTextField student_id_text,student_name_text,student_username_text;
        JTextField student_password_text,student_class_text,student_div_text;
        JLabel student_id_label,student_name_label,student_username_label;
        JLabel student_password_label,student_class_label,student_div_label;
       
        JButton submit;
       
       frame1=new JFrame("Add Student");

       add_student_panel=new JPanel();
       add_student_panel.setLayout(null);
       add_student_panel.setBounds(0,0,800,200);
       add_student_panel.setBackground(Color.BLUE);
      
       add_student_label=new JLabel();
       add_student_label.setText("Add Student");
       add_student_label.setBounds(150,90,300,50);
       add_student_label.setFont(new Font("Serif",Font.BOLD,30));
       add_student_label.setForeground(Color.WHITE);

       student_id_label=new JLabel();
       student_id_label.setText("Enter Student Rollno:");
       student_id_label.setFont(new Font("Serif",Font.BOLD,20));
       student_id_label.setForeground(Color.BLUE);
       student_id_label.setBounds(10,250,300,30);

       student_id_text=new JTextField();
       student_id_text.setToolTipText("Enter Student Rollno:");
       student_id_text.setFont(new Font("Serif",Font.BOLD,20));
       student_id_text.setForeground(Color.BLUE);
       student_id_text.setBounds(320,250,300,30);

       student_name_label=new JLabel();
       student_name_label.setText("Enter Student name:");
       student_name_label.setFont(new Font("Serif",Font.BOLD,20));
       student_name_label.setForeground(Color.BLUE);
       student_name_label.setBounds(10,300,300,30);
 
       student_name_text=new JTextField();
       student_name_text.setToolTipText("Enter Student name:");
       student_name_text.setFont(new Font("Serif",Font.BOLD,20));
       student_name_text.setForeground(Color.BLUE);
       student_name_text.setBounds(320,300,300,30);
 
       student_username_label=new JLabel();
       student_username_label.setText("Enter student username:");
       student_username_label.setFont(new Font("Serif",Font.BOLD,20));
       student_username_label.setForeground(Color.BLUE);
       student_username_label.setBounds(10,350,300,30);
 
       student_username_text=new JTextField();
       student_username_text.setToolTipText("Enter student username:");
       student_username_text.setFont(new Font("Serif",Font.BOLD,20));
       student_username_text.setForeground(Color.BLUE);
       student_username_text.setBounds(320,350,300,30);
 
       student_password_label=new JLabel();
       student_password_label.setText("Enter Student password:");
       student_password_label.setFont(new Font("Serif",Font.BOLD,20));
       student_password_label.setForeground(Color.BLUE);
       student_password_label.setBounds(10,400,300,30);

       student_password_text=new JTextField();
       student_password_text.setToolTipText("Enter Student password:");
       student_password_text.setFont(new Font("Serif",Font.BOLD,20));
       student_password_text.setForeground(Color.BLUE);
       student_password_text.setBounds(320,400,300,30);

       student_class_label=new JLabel();
       student_class_label.setText("Enter Student class:");
       student_class_label.setFont(new Font("Serif",Font.BOLD,20));
       student_class_label.setForeground(Color.BLUE);
       student_class_label.setBounds(10,450,300,30);

       student_class_text=new JTextField();
       student_class_text.setToolTipText("Enter Student class:");
       student_class_text.setFont(new Font("Serif",Font.BOLD,20));
       student_class_text.setForeground(Color.BLUE);
       student_class_text.setBounds(320,450,300,30);

       student_div_label=new JLabel();
       student_div_label.setText("Enter Student Div:");
       student_div_label.setFont(new Font("Serif",Font.BOLD,20));
       student_div_label.setForeground(Color.BLUE);
       student_div_label.setBounds(10,500,300,30);

       student_div_text=new JTextField();
       student_div_text.setToolTipText("Enter Student Div:");
       student_div_text.setFont(new Font("Serif",Font.BOLD,20));
       student_div_text.setForeground(Color.BLUE);
       student_div_text.setBounds(320,500,300,30);

       submit=new JButton();
       submit.setText("Submit");
       submit.setFont(new Font("Serif",Font.BOLD,15));
       submit.setForeground(Color.WHITE);
       submit.setBackground(Color.BLUE);
       submit.setBounds(100,550,150,30);
       
       previous2=new JButton("Previous");
       previous2.setText("Previous");
       previous2.setFont(new Font("Serif",Font.BOLD,15));
       previous2.setBounds(300,550,150,30);
       previous2.setForeground(Color.white);
       previous2.setBackground(Color.blue);
       
       add_student_panel.add(add_student_label);
       frame1.add(add_student_panel);
       frame1.add(student_id_label);
       frame1.add(student_id_text);
       frame1.add(student_name_label);
       frame1.add(student_name_text);
       frame1.add(student_username_label);
       frame1.add(student_password_label);
       frame1.add(student_username_text);
       frame1.add(student_password_text);
       frame1.add(student_class_label);
       frame1.add(student_class_text);
       frame1.add(student_div_label);
       frame1.add(student_div_text);
       frame1.add(submit);
       frame1.add(previous2);
        
        frame1.setSize(700,700);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.WHITE) ;        
    
        submit.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
            
            int id=Integer.parseInt(student_id_text.getText());
            String name=student_name_text.getText();
            String username=student_username_text.getText();
            String password=student_password_text.getText();
            String student_class=student_class_text.getText();
            String div=student_div_text.getText();
          
            try
            {
              Class.forName(JDBC_DRIVER).newInstance();
              conn=DriverManager.getConnection(DB_URL,USER,PASS);
              stmt=conn.createStatement();
              String sql;
               ResultSet rs=null;

                sql=String.format("Insert into Student values (%d,'%s','%s','%s','%s','%s',%d)",id,name,username,password,student_class,div,admin_id);
                ps=conn.prepareStatement(sql);
                int count=ps.executeUpdate(sql);
                if(count>0)
                {
                    frame1.setVisible(false);
                
                    JLabel success_label=new JLabel();
                    success_label.setText("Inserted Successfully");
                    success_label.setFont(new Font("Serif",Font.BOLD,25));
                    success_label.setForeground(Color.BLUE);
                    success_label.setBounds(100,650,500,30);
                    
                    frame1.add(success_label);
                    frame1.setVisible(true);
                }
    
             }
             catch(Exception E)
             {
                System.out.println(E);
             }

           }
       });
       previous2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
             frame1.setVisible(false);
             frame1.dispose();
             Admin_operations aobj=new Admin_operations(admin_name,admin_id); 
            }
         });
    }
    public void Update_Teacher(String admin_name,int admin_id)
    {
        JPanel update_teacher_panel;
        JLabel update_teacher_label,input_label;

        JLabel teacher_name_label,teacher_username_label,teacher_password_label;
        JLabel teacher_post_label,teacher_salary_label,teacher_mo_no_label;
        frame1=new JFrame("Update Teacher");
       
        update_teacher_panel=new JPanel();
        update_teacher_panel.setLayout(null);
        update_teacher_panel.setBounds(0,0,800,200);
        update_teacher_panel.setBackground(Color.BLUE);
       
        update_teacher_label=new JLabel();
        update_teacher_label.setText("Update Teacher");
        update_teacher_label.setBounds(150,90,300,50);
        update_teacher_label.setFont(new Font("Serif",Font.BOLD,25));
        update_teacher_label.setForeground(Color.WHITE);
 
        input_label=new JLabel();
        input_label.setText("Click on what do you want to update:");
        input_label.setBounds(10,210,800,30);
        input_label.setFont(new Font("Serif",Font.BOLD,25));
        input_label.setForeground(Color.BLUE);
 

        teacher_name_label=new JLabel();
        teacher_name_label.setText("* Update Teacher name");
        teacher_name_label.setBounds(10,250,800,30);
        teacher_name_label.setFont(new Font("Serif",Font.BOLD,25));
        teacher_name_label.setForeground(Color.BLUE.darker());
        teacher_name_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        teacher_username_label=new JLabel();
        teacher_username_label.setText("* Update Teacher username");
        teacher_username_label.setBounds(10,300,800,30);
        teacher_username_label.setFont(new Font("Serif",Font.BOLD,25));
        teacher_username_label.setForeground(Color.BLUE.darker());
        teacher_username_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        teacher_password_label=new JLabel();
        teacher_password_label.setText("* Update Teacher password");
        teacher_password_label.setBounds(10,350,800,30);
        teacher_password_label.setFont(new Font("Serif",Font.BOLD,25));
        teacher_password_label.setForeground(Color.BLUE.darker());
        teacher_password_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        teacher_post_label=new JLabel();
        teacher_post_label.setText("* Update Teacher post");
        teacher_post_label.setBounds(10,400,800,30);
        teacher_post_label.setFont(new Font("Serif",Font.BOLD,25));
        teacher_post_label.setForeground(Color.BLUE.darker());
        teacher_post_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        teacher_salary_label=new JLabel();
        teacher_salary_label.setText("* Update Teacher salary");
        teacher_salary_label.setBounds(10,450,800,30);
        teacher_salary_label.setFont(new Font("Serif",Font.BOLD,25));
        teacher_salary_label.setForeground(Color.BLUE.darker());
        teacher_salary_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        teacher_mo_no_label=new JLabel();
        teacher_mo_no_label.setText("* Update Teacher mobile number");
        teacher_mo_no_label.setBounds(10,500,800,30);
        teacher_mo_no_label.setFont(new Font("Serif",Font.BOLD,25));
        teacher_mo_no_label.setForeground(Color.BLUE.darker());
        teacher_mo_no_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      
        previous2=new JButton("Previous");
        previous2.setText("Previous");
        previous2.setFont(new Font("Serif",Font.BOLD,15));
        previous2.setBounds(500,550,150,40);
        previous2.setForeground(Color.white);
        previous2.setBackground(Color.blue);
       

        update_teacher_panel.add(update_teacher_label);
        frame1.add(update_teacher_panel);
        frame1.add(input_label);
        frame1.add(teacher_name_label);
        frame1.add(teacher_username_label);
        frame1.add(teacher_password_label);
        frame1.add(teacher_post_label);
        frame1.add(teacher_salary_label);
        frame1.add(teacher_mo_no_label);
        frame1.add(previous2);
        frame1.setSize(700,700);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.WHITE) ;        

        teacher_name_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);

               frame2=new JFrame("update_name");
               
               JPanel teacher_name_panel=new JPanel();
               teacher_name_panel.setBounds(0,0,700,200);
               teacher_name_panel.setLayout(null);
               teacher_name_panel.setBackground(Color.BLUE);

               JLabel update_teacher_name=new JLabel();
               update_teacher_name.setBounds(50,90,600,50);
               update_teacher_name.setForeground(Color.WHITE);
               update_teacher_name.setText("Update teacher new name");
               update_teacher_name.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_teacher_name_label=new JLabel();
               new_teacher_name_label.setBounds(10,250,350,40);
               new_teacher_name_label.setForeground(Color.BLUE);
               new_teacher_name_label.setText("Enter Teacher new name:");
               new_teacher_name_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_name=new JTextField();
               teacher_name.setBounds(360,250,300,40);
               teacher_name.setForeground(Color.BLUE);
               teacher_name.setToolTipText("Enter Teacher new name here:");
               teacher_name.setFont(new Font("Serif",Font.BOLD,20));

               JLabel new_teacher_id_label=new JLabel();
               new_teacher_id_label.setBounds(10,300,350,40);
               new_teacher_id_label.setForeground(Color.BLUE);
               new_teacher_id_label.setText("Enter Teacher id:");
               new_teacher_id_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_id=new JTextField();
               teacher_id.setBounds(360,300,300,40);
               teacher_id.setForeground(Color.BLUE);
               teacher_id.setToolTipText("Enter Teacher id here:");
               teacher_id.setFont(new Font("Serif",Font.BOLD,20));

               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,200,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);

               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));
 
               teacher_name_panel.add(update_teacher_name);
               frame2.add(teacher_name_panel);
               frame2.add(new_teacher_name_label);
               frame2.add(teacher_name);
               frame2.add(new_teacher_id_label);
               frame2.add(teacher_id);
               frame2.add(submit);
               frame2.add(previous3);

               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        
       
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       String teacher_uname=teacher_name.getText();
                       int t_id=Integer.parseInt(teacher_id.getText());
                       try
                       {

                           Class.forName(JDBC_DRIVER).newInstance();
                           conn=DriverManager.getConnection(DB_URL,USER,PASS);
                           stmt=conn.createStatement();
                           String sql;
                           ResultSet rs=null;

                           sql=String.format("Update Teacher set teacher_name='%s' where teacher_id=%d",teacher_uname,t_id);
                           ps=conn.prepareStatement(sql);
                           int count=ps.executeUpdate(sql);
                           if(count>0)
                           {
                              frame2.setVisible(false);
                              JLabel success_label=new JLabel();
                              success_label.setText("Updated Successfully");
                              success_label.setFont(new Font("Serif",Font.BOLD,25));
                              success_label.setForeground(Color.BLUE);
                              success_label.setBounds(100,550,500,30);
                            
                              frame2.add(success_label);
                              frame2.setVisible(true);
                        }
            
                     }
                     catch(Exception E)
                     {
                        System.out.println(E);
                     }
    
                   } 
               });
               previous3.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Teacher(admin_name,admin_id);
                  }
              });
              
            } 
        });
        teacher_username_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);
               frame2=new JFrame("update username");
               
               JPanel teacher_username_panel=new JPanel();
               teacher_username_panel.setBounds(0,0,700,200);
               teacher_username_panel.setLayout(null);
               teacher_username_panel.setBackground(Color.BLUE);

               JLabel update_teacher_username=new JLabel();
               update_teacher_username.setBounds(50,90,600,50);
               update_teacher_username.setForeground(Color.WHITE);
               update_teacher_username.setText("Update teacher new username");
               update_teacher_username.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_teacher_username_label=new JLabel();
               new_teacher_username_label.setBounds(10,250,350,40);
               new_teacher_username_label.setForeground(Color.BLUE);
               new_teacher_username_label.setText("Enter Teacher new username:");
               new_teacher_username_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_username=new JTextField();
               teacher_username.setBounds(360,250,300,40);
               teacher_username.setForeground(Color.BLUE);
               teacher_username.setToolTipText("Enter Teacher new username here:");
               teacher_username.setFont(new Font("Serif",Font.BOLD,20));

               JLabel new_teacher_id_label=new JLabel();
               new_teacher_id_label.setBounds(10,300,350,40);
               new_teacher_id_label.setForeground(Color.BLUE);
               new_teacher_id_label.setText("Enter Teacher id:");
               new_teacher_id_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_id=new JTextField();
               teacher_id.setBounds(360,300,300,40);
               teacher_id.setForeground(Color.BLUE);
               teacher_id.setToolTipText("Enter Teacher id here:");
               teacher_id.setFont(new Font("Serif",Font.BOLD,20));

               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,200,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);
 
               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));
 
               teacher_username_panel.add(update_teacher_username);
               frame2.add(teacher_username_panel);
               frame2.add(new_teacher_username_label);
               frame2.add(teacher_username);
               frame2.add(new_teacher_id_label);
               frame2.add(teacher_id);
               frame2.add(submit);
               frame2.add(previous3);
               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        
    
               previous3.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Teacher(admin_name,admin_id);
                  }
               });
              
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                    String teacher_uname=teacher_username.getText();
                    int t_id=Integer.parseInt(teacher_id.getText());
                    try
                    {

                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Teacher set teacher_username='%s' where teacher_id=%d",teacher_uname,t_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                     }
         
                  }
                  catch(Exception E)
                  {
                     System.out.println(E);
                  }
 
                   }
               });
            } 
        });
        teacher_password_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);
               frame2=new JFrame("update password");
               
               JPanel teacher_password_panel=new JPanel();
               teacher_password_panel.setBounds(0,0,700,200);
               teacher_password_panel.setLayout(null);
               teacher_password_panel.setBackground(Color.BLUE);

               JLabel update_teacher_password=new JLabel();
               update_teacher_password.setBounds(50,90,600,50);
               update_teacher_password.setForeground(Color.WHITE);
               update_teacher_password.setText("Update teacher new password");
               update_teacher_password.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_teacher_password_label=new JLabel();
               new_teacher_password_label.setBounds(10,250,350,40);
               new_teacher_password_label.setForeground(Color.BLUE);
               new_teacher_password_label.setText("Enter Teacher new password:");
               new_teacher_password_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_password=new JTextField();
               teacher_password.setBounds(360,250,300,40);
               teacher_password.setForeground(Color.BLUE);
               teacher_password.setToolTipText("Enter Teacher new password here:");
               teacher_password.setFont(new Font("Serif",Font.BOLD,20));

               JLabel new_teacher_id_label=new JLabel();
               new_teacher_id_label.setBounds(10,300,350,40);
               new_teacher_id_label.setForeground(Color.BLUE);
               new_teacher_id_label.setText("Enter Teacher id:");
               new_teacher_id_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_id=new JTextField();
               teacher_id.setBounds(360,300,300,40);
               teacher_id.setForeground(Color.BLUE);
               teacher_id.setToolTipText("Enter Teacher id here:");
               teacher_id.setFont(new Font("Serif",Font.BOLD,20));

               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,200,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);

               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));
 
               teacher_password_panel.add(update_teacher_password);
               frame2.add(teacher_password_panel);
               frame2.add(new_teacher_password_label);
               frame2.add(teacher_password);
               frame2.add(new_teacher_id_label);
               frame2.add(teacher_id);
               frame2.add(submit);
               frame2.add(previous3);

               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        
       
               previous3.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Teacher(admin_name,admin_id);
                  }
               });
   
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                    String teacher_pass=teacher_password.getText();
                    int t_id=Integer.parseInt(teacher_id.getText());
                    try
                    {

                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Teacher set teacher_password='%s' where teacher_id=%d",teacher_pass,t_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                     }
         
                  }
                  catch(Exception E)
                  {
                     System.out.println(E);
                  }
 
                   }
               });
            } 
        });
        teacher_post_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);
               frame2=new JFrame("update Post");

               JPanel teacher_post_panel=new JPanel();
               teacher_post_panel.setBounds(0,0,700,200);
               teacher_post_panel.setLayout(null);
               teacher_post_panel.setBackground(Color.BLUE);

               JLabel update_teacher_post=new JLabel();
               update_teacher_post.setBounds(50,90,600,50);
               update_teacher_post.setForeground(Color.WHITE);
               update_teacher_post.setText("Update teacher new post");
               update_teacher_post.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_teacher_post_label=new JLabel();
               new_teacher_post_label.setBounds(10,250,300,40);
               new_teacher_post_label.setForeground(Color.BLUE);
               new_teacher_post_label.setText("Enter Teacher new post:");
               new_teacher_post_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_post=new JTextField();
               teacher_post.setBounds(360,250,300,40);
               teacher_post.setForeground(Color.BLUE);
               teacher_post.setToolTipText("Enter Teacher new post here:");
               teacher_post.setFont(new Font("Serif",Font.BOLD,20));

               JLabel new_teacher_id_label=new JLabel();
               new_teacher_id_label.setBounds(10,300,350,40);
               new_teacher_id_label.setForeground(Color.BLUE);
               new_teacher_id_label.setText("Enter Teacher id:");
               new_teacher_id_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_id=new JTextField();
               teacher_id.setBounds(360,300,300,40);
               teacher_id.setForeground(Color.BLUE);
               teacher_id.setToolTipText("Enter Teacher id here:");
               teacher_id.setFont(new Font("Serif",Font.BOLD,20));

               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,200,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);

               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));
 
               teacher_post_panel.add(update_teacher_post);
               frame2.add(teacher_post_panel);
               frame2.add(new_teacher_post_label);
               frame2.add(teacher_post);
               frame2.add(new_teacher_id_label);
               frame2.add(teacher_id);
               frame2.add(submit);
               frame2.add(previous3);
              
               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        

               previous3.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Teacher(admin_name,admin_id);
                  }
               });
   
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                    String t_post=teacher_post.getText();
                    int t_id=Integer.parseInt(teacher_id.getText());
                    try
                    {

                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Teacher set teacher_post='%s' where teacher_id=%d",t_post,t_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                     }
         
                  }
                  catch(Exception E)
                  {
                     System.out.println(E);
                  }
 
                   }
               });
            } 
        });
        teacher_salary_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);

               frame2=new JFrame("update salary");
               JPanel teacher_salary_panel=new JPanel();
               teacher_salary_panel.setBounds(0,0,700,200);
               teacher_salary_panel.setLayout(null);
               teacher_salary_panel.setBackground(Color.BLUE);

               JLabel update_teacher_salary=new JLabel();
               update_teacher_salary.setBounds(50,90,600,50);
               update_teacher_salary.setForeground(Color.WHITE);
               update_teacher_salary.setText("Update teacher new salary");
               update_teacher_salary.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_teacher_salary_label=new JLabel();
               new_teacher_salary_label.setBounds(10,250,300,40);
               new_teacher_salary_label.setForeground(Color.BLUE);
               new_teacher_salary_label.setText("Enter Teacher new salary:");
               new_teacher_salary_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_salary=new JTextField();
               teacher_salary.setBounds(360,250,300,40);
               teacher_salary.setForeground(Color.BLUE);
               teacher_salary.setToolTipText("Enter Teacher new salary here:");
               teacher_salary.setFont(new Font("Serif",Font.BOLD,20));

               JLabel new_teacher_id_label=new JLabel();
               new_teacher_id_label.setBounds(10,300,350,40);
               new_teacher_id_label.setForeground(Color.BLUE);
               new_teacher_id_label.setText("Enter Teacher id:");
               new_teacher_id_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_id=new JTextField();
               teacher_id.setBounds(360,300,300,40);
               teacher_id.setForeground(Color.BLUE);
               teacher_id.setToolTipText("Enter Teacher id here:");
               teacher_id.setFont(new Font("Serif",Font.BOLD,20));

               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,200,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);
 
               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));

               teacher_salary_panel.add(update_teacher_salary);
               frame2.add(teacher_salary_panel);
               frame2.add(new_teacher_salary_label);
               frame2.add(teacher_salary);
               frame2.add(new_teacher_id_label);
               frame2.add(teacher_id);
               frame2.add(submit);
               frame2.add(previous3);
              
               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        
       
               previous3.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Teacher(admin_name,admin_id);
                  }
               });
   
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                    int t_salary=Integer.parseInt(teacher_salary.getText());
                    int t_id=Integer.parseInt(teacher_id.getText());
                    try
                    {

                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Teacher set teacher_salary=%d where teacher_id=%d",t_salary,t_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                     }
         
                  }
                  catch(Exception E)
                  {
                     System.out.println(E);
                  }
 
                   }
               });
            } 
        });
        teacher_mo_no_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);
               frame2=new JFrame("update mobile number");
         
               JPanel teacher_name_panel=new JPanel();
               teacher_name_panel.setBounds(0,0,700,200);
               teacher_name_panel.setLayout(null);
               teacher_name_panel.setBackground(Color.BLUE);

               JLabel update_teacher_name=new JLabel();
               update_teacher_name.setBounds(50,90,600,50);
               update_teacher_name.setForeground(Color.WHITE);
               update_teacher_name.setText("Update Teacher mobile number");
               update_teacher_name.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_teacher_name_label=new JLabel();
               new_teacher_name_label.setBounds(10,250,400,40);
               new_teacher_name_label.setForeground(Color.BLUE);
               new_teacher_name_label.setText("Enter Teacher new mobile number:");
               new_teacher_name_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_mo_no=new JTextField();
               teacher_mo_no.setBounds(410,250,250,40);
               teacher_mo_no.setForeground(Color.BLUE);
               teacher_mo_no.setToolTipText("Enter teacher new mobile number here:");
               teacher_mo_no.setFont(new Font("Serif",Font.BOLD,20));

               JLabel new_teacher_id_label=new JLabel();
               new_teacher_id_label.setBounds(10,300,350,40);
               new_teacher_id_label.setForeground(Color.BLUE);
               new_teacher_id_label.setText("Enter Teacher id:");
               new_teacher_id_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField teacher_id=new JTextField();
               teacher_id.setBounds(410,300,250,40);
               teacher_id.setForeground(Color.BLUE);
               teacher_id.setToolTipText("Enter Teacher id here:");
               teacher_id.setFont(new Font("Serif",Font.BOLD,20));

               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,200,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);

               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));

               teacher_name_panel.add(update_teacher_name);
               frame2.add(teacher_name_panel);
               frame2.add(new_teacher_name_label);
               frame2.add(teacher_mo_no);
               frame2.add(new_teacher_id_label);
               frame2.add(teacher_id);
               frame2.add(submit);
               frame2.add(previous3);

               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        
   
               previous3.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent ae)
                  {
                      frame2.setVisible(false);
                      frame2.dispose();
                      Update_Teacher(admin_name,admin_id);
                  }
               });
   
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                    int t_mo_no=Integer.parseInt(teacher_mo_no.getText());
                    int t_id=Integer.parseInt(teacher_id.getText());
                    try
                    {

                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Teacher set teacher_mobile_no=%d where teacher_id=%d",t_mo_no,t_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                     }
         
                  }
                  catch(Exception E)
                  {
                     System.out.println(E);
                  }
 
                   }
               });
            } 
        });
        previous2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
             frame1.setVisible(false);
             frame1.dispose();
             Admin_operations aobj=new Admin_operations(admin_name,admin_id); 
            }
         });
 

    }
    public void Update_Student(String admin_name,int admin_id)
    {
        JPanel update_student_panel;
        JLabel update_student_label,input_label;
        JLabel student_name_label,student_username_label,student_password_label,student_class_label;
        JLabel student_div_label,new_student_name_label;

        JPanel student_name_update_panel;
        frame1=new JFrame("Update Student");
        update_student_panel=new JPanel();
        update_student_panel.setLayout(null);
        update_student_panel.setBounds(0,0,800,200);
        update_student_panel.setBackground(Color.BLUE);
      
        update_student_label=new JLabel();
        update_student_label.setText("Update Student");
        update_student_label.setBounds(150,90,300,50);
        update_student_label.setFont(new Font("Serif",Font.BOLD,30));
        update_student_label.setForeground(Color.WHITE);

        input_label=new JLabel();
        input_label.setText("Click on what do you want to update:");
        input_label.setBounds(10,210,800,30);
        input_label.setFont(new Font("Serif",Font.BOLD,25));
        input_label.setForeground(Color.BLUE);
 
        student_name_label=new JLabel();
        student_name_label.setText("* Update student name");
        student_name_label.setBounds(10,300,800,30);
        student_name_label.setFont(new Font("Serif",Font.BOLD,25));
        student_name_label.setForeground(Color.BLUE.darker());
        student_name_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        student_username_label=new JLabel();
        student_username_label.setText("* Update Student username");
        student_username_label.setBounds(10,350,800,30);
        student_username_label.setFont(new Font("Serif",Font.BOLD,25));
        student_username_label.setForeground(Color.BLUE.darker());
        student_username_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        student_password_label=new JLabel();
        student_password_label.setText("* Update Student password");
        student_password_label.setBounds(10,400,800,30);
        student_password_label.setFont(new Font("Serif",Font.BOLD,25));
        student_password_label.setForeground(Color.BLUE.darker());
        student_password_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        student_class_label=new JLabel();
        student_class_label.setText("* Update Student Class");
        student_class_label.setBounds(10,450,800,30);
        student_class_label.setFont(new Font("Serif",Font.BOLD,25));
        student_class_label.setForeground(Color.BLUE.darker());
        student_class_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        student_div_label=new JLabel();
        student_div_label.setText("* Update Student Div");
        student_div_label.setBounds(10,500,800,30);
        student_div_label.setFont(new Font("Serif",Font.BOLD,25));
        student_div_label.setForeground(Color.BLUE.darker());
        student_div_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        previous2=new JButton("Previous");
        previous2.setText("Previous");
        previous2.setFont(new Font("Serif",Font.BOLD,15));
        previous2.setBounds(450,550,150,30);
        previous2.setForeground(Color.white);
        previous2.setBackground(Color.blue);
       
        update_student_panel.add(update_student_label);
        frame1.add(update_student_panel);
        frame1.add(input_label);
        frame1.add(student_name_label);
        frame1.add(student_username_label);
        frame1.add(student_password_label);
        frame1.add(student_class_label);
        frame1.add(student_div_label);
        frame1.add(previous2);

        frame1.setSize(700,700);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.WHITE) ;        

        student_name_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);
                frame2=new JFrame("update student name");
                 
                JPanel student_name_panel=new JPanel();
                student_name_panel.setBounds(0,0,700,200);
                student_name_panel.setLayout(null);
                student_name_panel.setBackground(Color.BLUE);

                JLabel update_student_name=new JLabel();
                update_student_name.setBounds(50,90,600,50);
                update_student_name.setForeground(Color.WHITE);
                update_student_name.setText("Update Student new name");
                update_student_name.setFont(new Font("Serif",Font.BOLD,25));

                JLabel new_student_name_label=new JLabel();
                new_student_name_label.setBounds(10,250,300,40);
                new_student_name_label.setForeground(Color.BLUE);
                new_student_name_label.setText("Enter Student new name:");
                new_student_name_label.setFont(new Font("Serif",Font.BOLD,20));

                JTextField student_name=new JTextField();
                student_name.setBounds(360,250,300,40);
                student_name.setForeground(Color.BLUE);
                student_name.setToolTipText("Enter Student new name here:");
                student_name.setFont(new Font("Serif",Font.BOLD,20));

                JLabel new_student_id_label=new JLabel();
                new_student_id_label.setBounds(10,300,350,40);
                new_student_id_label.setForeground(Color.BLUE);
                new_student_id_label.setText("Enter student Rollno:");
                new_student_id_label.setFont(new Font("Serif",Font.BOLD,20));
 
                JTextField student_id=new JTextField();
                student_id.setBounds(360,300,300,40);
                student_id.setForeground(Color.BLUE);
                student_id.setToolTipText("Enter Student rollno here:");
                student_id.setFont(new Font("Serif",Font.BOLD,20));
 
                JButton submit=new JButton("Submit");
                submit.setBounds(100,400,150,30);
                submit.setFont(new Font("Serif",Font.BOLD,15));
                submit.setBackground(Color.BLUE);
                submit.setForeground(Color.WHITE);

                JButton previous3=new JButton("Previous");
                previous3.setBackground(Color.BLUE);
                previous3.setForeground(Color.WHITE);
                previous3.setBounds(300,400,150,30);
                previous3.setFont(new Font("Serif",Font.BOLD,15));
  

                student_name_panel.add(update_student_name);
                frame2.add(student_name_panel);
                frame2.add(new_student_name_label);
                frame2.add(student_name);
                frame2.add(new_student_id_label);
                frame2.add(student_id);
                frame2.add(submit);
                frame2.add(previous3);

                frame2.setSize(700,600);
                frame2.setLayout(null);
                frame2.setVisible(true);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.getContentPane().setBackground(Color.WHITE) ; 
           
                previous3.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        frame2.setVisible(false);
                        frame2.dispose();
                        Update_Student(admin_name,admin_id);
                    }
                });
       
                submit.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        String s_name=student_name.getText();
                        int s_id=Integer.parseInt(student_id.getText());
                        try
                        {
    
                            Class.forName(JDBC_DRIVER).newInstance();
                            conn=DriverManager.getConnection(DB_URL,USER,PASS);
                            stmt=conn.createStatement();
                            String sql;
                            ResultSet rs=null;
    
                            sql=String.format("Update Student set student_name='%s' where Rollno=%d",s_name,s_id);
                            ps=conn.prepareStatement(sql);
                            int count=ps.executeUpdate(sql);
                            if(count>0)
                            {
                               frame2.setVisible(false);
                               JLabel success_label=new JLabel();
                               success_label.setText("Updated Successfully");
                               success_label.setFont(new Font("Serif",Font.BOLD,25));
                               success_label.setForeground(Color.BLUE);
                               success_label.setBounds(100,550,500,30);
                             
                               frame2.add(success_label);
                               frame2.setVisible(true);
                         }
             
                      }
                      catch(Exception E)
                      {
                         System.out.println(E);
                      }

                    }
                });
       
            } 
        });
        student_username_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);
               frame2=new JFrame("Update Student Username");

               JPanel student_name_panel=new JPanel();
               student_name_panel.setBounds(0,0,700,200);
               student_name_panel.setLayout(null);
               student_name_panel.setBackground(Color.BLUE);

               JLabel update_student_username=new JLabel();
               update_student_username.setBounds(50,90,600,50);
               update_student_username.setForeground(Color.WHITE);
               update_student_username.setText("Update Student new username");
               update_student_username.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_student_username_label=new JLabel();
               new_student_username_label.setBounds(10,250,350,40);
               new_student_username_label.setForeground(Color.BLUE);
               new_student_username_label.setText("Enter Student new username:");
               new_student_username_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField student_username=new JTextField();
               student_username.setBounds(360,250,300,40);
               student_username.setForeground(Color.BLUE);
               student_username.setToolTipText("Enter Student new username here:");
               student_username.setFont(new Font("Serif",Font.BOLD,20));

                JLabel new_student_id_label=new JLabel();
                new_student_id_label.setBounds(10,300,350,40);
                new_student_id_label.setForeground(Color.BLUE);
                new_student_id_label.setText("Enter student Rollno:");
                new_student_id_label.setFont(new Font("Serif",Font.BOLD,20));
 
                JTextField student_id=new JTextField();
                student_id.setBounds(360,300,300,40);
                student_id.setForeground(Color.BLUE);
                student_id.setToolTipText("Enter Student Rollno here:");
                student_id.setFont(new Font("Serif",Font.BOLD,20));
 
               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,150,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);
 
               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));
 
               student_name_panel.add(update_student_username);
               frame2.add(student_name_panel);
               frame2.add(new_student_username_label);
               frame2.add(student_username);
               frame2.add(new_student_id_label);
               frame2.add(student_id);
               frame2.add(submit);
               frame2.add(previous3);

               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        
       
               previous3.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent ae)
                   {
                       frame2.setVisible(false);
                       frame2.dispose();
                       Update_Student(admin_name,admin_id);
                   }
               });
      
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                    String s_uname=student_username.getText();
                    int s_id=Integer.parseInt(student_id.getText());
                    try
                    {

                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Student set student_username='%s' where Rollno=%d",s_uname,s_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                     }
         
                  }
                  catch(Exception E)
                  {
                     System.out.println(E);
                  }

                   }
               });
            } 
        });
        student_password_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);
               frame2=new JFrame("update student password");
               JPanel student_name_panel=new JPanel();
               student_name_panel.setBounds(0,0,700,200);
               student_name_panel.setLayout(null);
               student_name_panel.setBackground(Color.BLUE);

               JLabel update_student_password=new JLabel();
               update_student_password.setBounds(50,90,600,50);
               update_student_password.setForeground(Color.WHITE);
               update_student_password.setText("Update Student new password");
               update_student_password.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_student_password_label=new JLabel();
               new_student_password_label.setBounds(10,250,350,40);
               new_student_password_label.setForeground(Color.BLUE);
               new_student_password_label.setText("Enter Student new password:");
               new_student_password_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField student_password=new JTextField();
               student_password.setBounds(360,250,300,40);
               student_password.setForeground(Color.BLUE);
               student_password.setToolTipText("Enter Student new password here:");
               student_password.setFont(new Font("Serif",Font.BOLD,20));

                JLabel new_student_id_label=new JLabel();
                new_student_id_label.setBounds(10,300,350,40);
                new_student_id_label.setForeground(Color.BLUE);
                new_student_id_label.setText("Enter student Rollno:");
                new_student_id_label.setFont(new Font("Serif",Font.BOLD,20));
 
                JTextField student_id=new JTextField();
                student_id.setBounds(360,300,300,40);
                student_id.setForeground(Color.BLUE);
                student_id.setToolTipText("Enter Student Rollno here:");
                student_id.setFont(new Font("Serif",Font.BOLD,20));
 
               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,150,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);
 
               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));

               student_name_panel.add(update_student_password);
               frame2.add(student_name_panel);
               frame2.add(new_student_password_label);
               frame2.add(student_password);
               frame2.add(new_student_id_label);
               frame2.add(student_id);
               frame2.add(submit);
               frame2.add(previous3);

               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        

               previous3.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent ae)
                   {
                       frame2.setVisible(false);
                       frame2.dispose();
                       Update_Student(admin_name,admin_id);
                   }
               });
    
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                    String s_pass=student_password.getText();
                    int s_id=Integer.parseInt(student_id.getText());
                    try
                    {

                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Student set student_password='%s' where Rollno=%d",s_pass,s_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                     }
         
                  }
                  catch(Exception E)
                  {
                     System.out.println(E);
                  }

                   }
               });
            } 
        });
        student_class_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);
               frame2=new JFrame("update student class");
               
               JPanel student_name_panel=new JPanel();
               student_name_panel.setBounds(0,0,700,200);
               student_name_panel.setLayout(null);
               student_name_panel.setBackground(Color.BLUE);

               JLabel update_student_class=new JLabel();
               update_student_class.setBounds(50,90,600,50);
               update_student_class.setForeground(Color.WHITE);
               update_student_class.setText("Update Student new class");
               update_student_class.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_student_class_label=new JLabel();
               new_student_class_label.setBounds(10,250,350,40);
               new_student_class_label.setForeground(Color.BLUE);
               new_student_class_label.setText("Enter Student new class:");
               new_student_class_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField student_class=new JTextField();
               student_class.setBounds(360,250,300,40);
               student_class.setForeground(Color.BLUE);
               student_class.setToolTipText("Enter Student new class here:");
               student_class.setFont(new Font("Serif",Font.BOLD,20));

                JLabel new_student_id_label=new JLabel();
                new_student_id_label.setBounds(10,300,350,40);
                new_student_id_label.setForeground(Color.BLUE);
                new_student_id_label.setText("Enter student Rollno:");
                new_student_id_label.setFont(new Font("Serif",Font.BOLD,20));
 
                JTextField student_id=new JTextField();
                student_id.setBounds(360,300,300,40);
                student_id.setForeground(Color.BLUE);
                student_id.setToolTipText("Enter Student Rollno here:");
                student_id.setFont(new Font("Serif",Font.BOLD,20));
 
               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,150,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);

               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));


               student_name_panel.add(update_student_class);
               frame2.add(student_name_panel);
               frame2.add(new_student_class_label);
               frame2.add(student_class);
               frame2.add(new_student_id_label);
               frame2.add(student_id);
               frame2.add(submit);
               frame2.add(previous3);

               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        
       
               previous3.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent ae)
                   {
                       frame2.setVisible(false);
                       frame2.dispose();
                       Update_Student(admin_name,admin_id);
                   }
               });
    
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                    String s_class=student_class.getText();
                    int s_id=Integer.parseInt(student_id.getText());
                    try
                    {

                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Student set student_class='%s' where Rollno=%d",s_class,s_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                     }
         
                  }
                  catch(Exception E)
                  {
                     System.out.println(E);
                  }

                   }
               });
            } 
        });
        student_div_label.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                frame1.setVisible(false);
               frame2=new JFrame("update student division");
               JPanel student_name_panel=new JPanel();
               student_name_panel.setBounds(0,0,700,200);
               student_name_panel.setLayout(null);
               student_name_panel.setBackground(Color.BLUE);

               JLabel update_student_div=new JLabel();
               update_student_div.setBounds(50,90,600,50);
               update_student_div.setForeground(Color.WHITE);
               update_student_div.setText("Update Student new division");
               update_student_div.setFont(new Font("Serif",Font.BOLD,25));

               JLabel new_student_div_label=new JLabel();
               new_student_div_label.setBounds(10,250,350,40);
               new_student_div_label.setForeground(Color.BLUE);
               new_student_div_label.setText("Enter Student new division:");
               new_student_div_label.setFont(new Font("Serif",Font.BOLD,20));

               JTextField student_div=new JTextField();
               student_div.setBounds(360,250,300,40);
               student_div.setForeground(Color.BLUE);
               student_div.setToolTipText("Enter Student new division here:");
               student_div.setFont(new Font("Serif",Font.BOLD,20));

                JLabel new_student_id_label=new JLabel();
                new_student_id_label.setBounds(10,300,350,40);
                new_student_id_label.setForeground(Color.BLUE);
                new_student_id_label.setText("Enter student Rollno:");
                new_student_id_label.setFont(new Font("Serif",Font.BOLD,20));
 
                JTextField student_id=new JTextField();
                student_id.setBounds(360,300,300,40);
                student_id.setForeground(Color.BLUE);
                student_id.setToolTipText("Enter Student Rollno here:");
                student_id.setFont(new Font("Serif",Font.BOLD,20));
 
               JButton submit=new JButton("Submit");
               submit.setBounds(100,400,150,30);
               submit.setFont(new Font("Serif",Font.BOLD,15));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);

               JButton previous3=new JButton("Previous");
               previous3.setBackground(Color.BLUE);
               previous3.setForeground(Color.WHITE);
               previous3.setBounds(350,400,150,30);
               previous3.setFont(new Font("Serif",Font.BOLD,15));

               student_name_panel.add(update_student_div);
               frame2.add(student_name_panel);
               frame2.add(new_student_div_label);
               frame2.add(student_div);
               frame2.add(new_student_id_label);
               frame2.add(student_id);
               frame2.add(submit);
               frame2.add(previous3);

               frame2.setSize(700,600);
               frame2.setLayout(null);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame2.getContentPane().setBackground(Color.WHITE) ;        
    
               previous3.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent ae)
                   {
                       frame2.setVisible(false);
                       frame2.dispose();
                       Update_Student(admin_name,admin_id);
                   }
               });   
               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                    String s_div=student_div.getText();
                    int s_id=Integer.parseInt(student_id.getText());
                    try
                    {

                        Class.forName(JDBC_DRIVER).newInstance();
                        conn=DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt=conn.createStatement();
                        String sql;
                        ResultSet rs=null;

                        sql=String.format("Update Student set student_div='%s' where Rollno=%d",s_div,s_id);
                        ps=conn.prepareStatement(sql);
                        int count=ps.executeUpdate(sql);
                        if(count>0)
                        {
                           frame2.setVisible(false);
                           JLabel success_label=new JLabel();
                           success_label.setText("Updated Successfully");
                           success_label.setFont(new Font("Serif",Font.BOLD,25));
                           success_label.setForeground(Color.BLUE);
                           success_label.setBounds(100,550,500,30);
                         
                           frame2.add(success_label);
                           frame2.setVisible(true);
                     }
         
                  }
                  catch(Exception E)
                  {
                     System.out.println(E);
                  }

                   }
               });
            } 
        });
        previous2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
             frame1.setVisible(false);
             frame1.dispose();
             Admin_operations aobj=new Admin_operations(admin_name,admin_id); 
            }
         });
 
    }
    public void Delete_Teacher(String admin_name,int admin_id)
    {
        frame1=new JFrame("Delete Teacher");
        JPanel delete_teacher_panel;
        JLabel delete_teacher_label,teacher_id_label;
        JTextField teacher_id_text;
        JButton submit;

        delete_teacher_panel=new JPanel();
        delete_teacher_panel.setLayout(null);
        delete_teacher_panel.setBounds(0,0,600,200);
        delete_teacher_panel.setBackground(Color.BLUE);
      
        delete_teacher_label=new JLabel();
        delete_teacher_label.setText("Delete Teacher");
        delete_teacher_label.setBounds(150,90,300,50);
        delete_teacher_label.setFont(new Font("Serif",Font.BOLD,30));
        delete_teacher_label.setForeground(Color.WHITE);

        delete_teacher_label=new JLabel();
        delete_teacher_label.setText("Delete Teacher");
        delete_teacher_label.setBounds(150,90,300,50);
        delete_teacher_label.setFont(new Font("Serif",Font.BOLD,30));
        delete_teacher_label.setForeground(Color.WHITE);

        teacher_id_label=new JLabel();
        teacher_id_label.setText("Enter Teacher id:");
        teacher_id_label.setFont(new Font("Serif",Font.BOLD,20));
        teacher_id_label.setBounds(10,300,250,30);
        teacher_id_label.setForeground(Color.BLUE);

        teacher_id_text=new JTextField();
        teacher_id_text.setToolTipText("Enter Teacher id:");
        teacher_id_text.setFont(new Font("Serif",Font.BOLD,20));
        teacher_id_text.setBounds(300,300,200,30);
        teacher_id_text.setBackground(Color.WHITE);
        teacher_id_text.setForeground(Color.BLUE);

        submit=new JButton();
        submit.setText("Submit");
        submit.setFont(new Font("Serif",Font.BOLD,15));
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLUE);
        submit.setBounds(100,400,150,30);
 
        previous2=new JButton("Previous");
        previous2.setText("Previous");
        previous2.setFont(new Font("Serif",Font.BOLD,15));
        previous2.setBounds(300,400,150,30);
        previous2.setForeground(Color.white);
        previous2.setBackground(Color.blue);
       
        delete_teacher_panel.add(delete_teacher_label);
        frame1.add(delete_teacher_panel);
        frame1.add(teacher_id_label);
        frame1.add(teacher_id_text);
        frame1.add(submit);

        frame1.add(previous2);

        frame1.setSize(600,600);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.WHITE) ;        

        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int t_id=Integer.parseInt(teacher_id_text.getText());
                try
                {

                    Class.forName(JDBC_DRIVER).newInstance();
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
                    stmt=conn.createStatement();
                    String sql;
                    ResultSet rs=null;

                    sql=String.format("Delete from Teacher where teacher_id=%d",t_id);
                    ps=conn.prepareStatement(sql);
                    int count=ps.executeUpdate(sql);
                    if(count>0)
                    {
                       frame1.setVisible(false);
                       JLabel success_label=new JLabel();
                       success_label.setText("Deleted Successfully");
                       success_label.setFont(new Font("Serif",Font.BOLD,25));
                       success_label.setForeground(Color.BLUE);
                       success_label.setBounds(100,550,500,30);
                     
                       frame1.add(success_label);
                       frame1.setVisible(true);
                 }
     
              }
              catch(Exception E)
              {
                 System.out.println(E);
              }

            }
        });
        previous2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
             frame1.setVisible(false);
             frame1.dispose();
             Admin_operations aobj=new Admin_operations(admin_name,admin_id); 
            }
         });
    }
    public void Delete_Student(String admin_name,int admin_id)
    {
        frame1=new JFrame("Delete Student");
        JPanel delete_student_panel;
        JLabel delete_student_label,student_id_label;
        JTextField student_id_text;
        JButton submit;

        delete_student_panel=new JPanel();
        delete_student_panel.setLayout(null);
        delete_student_panel.setBounds(0,0,600,200);
        delete_student_panel.setBackground(Color.BLUE);
      
        delete_student_label=new JLabel();
        delete_student_label.setText("Delete Student");
        delete_student_label.setBounds(150,90,300,50);
        delete_student_label.setFont(new Font("Serif",Font.BOLD,30));
        delete_student_label.setForeground(Color.WHITE);

        student_id_label=new JLabel();
        student_id_label.setText("Enter Student Rollno:");
        student_id_label.setFont(new Font("Serif",Font.BOLD,20));
        student_id_label.setBounds(10,300,250,30);
        student_id_label.setForeground(Color.BLUE);

        student_id_text=new JTextField();
        student_id_text.setToolTipText("Enter Student Rollno:");
        student_id_text.setFont(new Font("Serif",Font.BOLD,20));
        student_id_text.setBounds(300,300,200,30);
        student_id_text.setBackground(Color.WHITE);
        student_id_text.setForeground(Color.BLUE);

        submit=new JButton();
        submit.setText("Submit");
        submit.setFont(new Font("Serif",Font.BOLD,15));
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLUE);
        submit.setBounds(100,400,150,30);
       
        previous2=new JButton("Previous");
        previous2.setText("Previous");
        previous2.setFont(new Font("Serif",Font.BOLD,15));
        previous2.setBounds(300,400,150,30);
        previous2.setForeground(Color.white);
        previous2.setBackground(Color.blue);
       
        delete_student_panel.add(delete_student_label);
        frame1.add(delete_student_panel);
        frame1.add(student_id_label);
        frame1.add(student_id_text);
        frame1.add(submit);
        frame1.add(previous2);

        frame1.setSize(600,600);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.WHITE) ;        

        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int s_id=Integer.parseInt(student_id_text.getText());
                try
                {

                    Class.forName(JDBC_DRIVER).newInstance();
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
                    stmt=conn.createStatement();
                    String sql;
                    ResultSet rs=null;

                    sql=String.format("Delete from Student where Rollno=%d",s_id);
                    ps=conn.prepareStatement(sql);
                    int count=ps.executeUpdate(sql);
                    if(count>0)
                    {
                       frame1.setVisible(false);
                       JLabel success_label=new JLabel();
                       success_label.setText("Deleted Successfully");
                       success_label.setFont(new Font("Serif",Font.BOLD,25));
                       success_label.setForeground(Color.BLUE);
                       success_label.setBounds(100,550,500,30);
                     
                       frame1.add(success_label);
                       frame1.setVisible(true);
                 }
     
              }
              catch(Exception E)
              {
                 System.out.println(E);
              }

            }
        });
        previous2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
             frame1.setVisible(false);
             frame1.dispose();
             Admin_operations aobj=new Admin_operations(admin_name,admin_id); 
            }
         });

    }
}
class Teacher extends JFrame
{
    JFrame frame;
    JPanel welcome_panel;
    JLabel welcome_label;
    
    JLabel username_label,password_label,wrong_uname_pass,wrong_pass,wrong_uname;
    JTextField username;
    JPasswordField password;
    JButton submit;
    String uname;
    String pass;
    String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
        
    String DB_URL="jdbc:mysql://localhost/Computer_Department";

    String USER ="root";
    String PASS ="";

    Connection conn=null;
    Statement stmt=null;

    Teacher()
    {
        frame=new JFrame("Teacher login");
        frame.setSize(600,600);
       
        welcome_panel=new JPanel();
        welcome_panel.setBounds(0,0,600,200);
        welcome_panel.setLayout(null);
        welcome_panel.setBackground(Color.BLUE);

        welcome_label=new JLabel();
        welcome_label.setText("Welcome in Teacher Login");
        welcome_label.setBounds(10,90,600,50);
        welcome_label.setForeground(Color.WHITE);
        welcome_label.setFont(new Font("Serif",Font.BOLD,30));
       
        username_label=new JLabel("Username:");
        username_label.setForeground(Color.BLUE);
        username_label.setBounds(30,300,100,30);
        username_label.setFont(new Font("Serif",Font.BOLD,16));

        username=new JTextField();
        username.setToolTipText("Enter your username");
        username.setForeground(Color.BLUE);
        username.setFont(new Font("Serif",Font.BOLD,15));
        username.setBounds(200,300,200,30);

        password_label=new JLabel("Password:");
        password_label.setForeground(Color.BLUE);
        password_label.setBounds(30,400,100,30);
        password_label.setFont(new Font("Serif",Font.BOLD,16));

        password=new JPasswordField();
        password.setToolTipText("Enter your password");
        password.setForeground(Color.BLUE);
        password.setBounds(200,400,200,30);

        submit=new JButton("Submit");
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.WHITE);
        submit.setBounds(100,500,150,30);
        
        JButton previous=new JButton("Previous");
        previous.setBackground(Color.BLUE);
        previous.setForeground(Color.white);
        previous.setBounds(350,500,150,30);

        welcome_panel.add(welcome_label);
        frame.add(welcome_panel);
        frame.add(username_label);
        frame.add(username);
        frame.add(password_label);
        frame.add(password);
        frame.add(submit);
        frame.add(previous);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
       
        previous.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.setVisible(false);
                frame.dispose();
                School_Management_System obj=new School_Management_System();         
            }
        });
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                uname=username.getText();
                pass=new String(password.getPassword());

                try
                {
                  Class.forName(JDBC_DRIVER).newInstance();
        
                  
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
        
                    
                    stmt=conn.createStatement();
                    String sql;
        
                    sql="Select teacher_id,teacher_username,teacher_password,teacher_name from Teacher";
                    ResultSet rs=stmt.executeQuery(sql);
        
                    while(rs.next())
                    {
                        String teacher_username=rs.getString("teacher_username");
                        String teacher_password=rs.getString("teacher_password");
                        String teacher_name=rs.getString("teacher_name");
                        int teacher_id=rs.getInt("teacher_id");
                        if((teacher_username.equals(uname))&&(teacher_password.equals(pass)))
                        {
                           frame.setVisible(false);
                           Teacher_operations teacherobj=new Teacher_operations(teacher_name,teacher_id);
                            
                        }
                    }
        
                   
                 }
                 catch(Exception E)
                 {
                    System.out.println(E);
                 }
    
            }       
        });
    }
}
class Teacher_operations extends JFrame
{
    JFrame frame,frame1;
    JLabel welcome_label,select,success_label;
    JLabel subject_id_label,rollno_label,mark_label,update_label,delete_label;
    JPanel login_panel,update_panel,delete_panel;
    JButton information,attendance,update,delete,submit;
    JTextField rollno_text,subject_id_text,mark_text;

    String uname;
    String pass;
    int rollno,subject_id,new_mark;
    String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
        
    String DB_URL="jdbc:mysql://localhost/Computer_Department";

    int attendance_id[];
    Date date[];
    String status[];
    int size;

    String USER ="root";
    String PASS ="";
    
    Connection conn=null;
    PreparedStatement ps=null;
    Statement stmt=null;

 
    Teacher_operations(String teacher_name,int teacher_id)
    {
        frame=new JFrame("Teacher Login");
        frame.setSize(600,700);

        login_panel=new JPanel();
        login_panel.setBounds(0,0,600,200);
        login_panel.setLayout(null);
        login_panel.setBackground(Color.BLUE);

        welcome_label=new JLabel();
        welcome_label.setText("Welcome : "+teacher_name);
        welcome_label.setBounds(10,90,600,50);
        welcome_label.setFont(new Font("Serif",Font.BOLD,30));
        welcome_label.setForeground(Color.WHITE);

        select=new JLabel();
        select.setText("Select what do you want to perform:");
        select.setBounds(10,230,600,50);
        select.setFont(new Font("Serif",Font.BOLD,20));
        select.setForeground(Color.BLUE);

        information=new JButton("Your Information");
        information.setText("Your Information");
        information.setFont(new Font("Serif",Font.BOLD,15));
        information.setBounds(200,300,200,50);
        information.setForeground(Color.WHITE);
        information.setBackground(Color.BLUE);


        attendance=new JButton("Your Attendance");
        attendance.setText("Your Attendance");
        attendance.setFont(new Font("Serif",Font.BOLD,15));
        attendance.setBounds(200,400,200,50);
        attendance.setForeground(Color.WHITE);
        attendance.setBackground(Color.BLUE);
        
        update=new JButton("update");
        update.setText("Update mark");
        update.setFont(new Font("Serif",Font.BOLD,15));
        update.setBackground(Color.BLUE);
        update.setForeground(Color.WHITE);
        update.setBounds(200,500,200,50);

        delete=new JButton("delete");
        delete.setText("Delete mark");
        delete.setFont(new Font("Serif",Font.BOLD,15));
        delete.setBackground(Color.BLUE);
        delete.setForeground(Color.WHITE);
        delete.setBounds(200,600,200,50);

        login_panel.add(welcome_label);
        
        frame.add(login_panel);
        frame.add(select);
        frame.add(information);
        frame.add(attendance);
        frame.add(update);
        frame.add(delete);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE) ;
        
        information.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
                try
                {
                  Class.forName(JDBC_DRIVER).newInstance();

                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
                    stmt=conn.createStatement();
                    String sql;
        
                    sql="select teacher_id,teacher_name,teacher_post,teacher_salary,teacher_username,teacher_password,teacher_mobile_no from Teacher";
                    ResultSet rs=stmt.executeQuery(sql);
        
                    while(rs.next())
                    {
                        int teacher_identityno=rs.getInt("teacher_id");
                        if(teacher_identityno==teacher_id)
                        {
                            frame.setVisible(false);
                            String teacher_name=rs.getString("teacher_name");
                            String teacher_post=rs.getString("teacher_post");
                            String teacher_salary=rs.getString("teacher_salary");
                            String teacher_username=rs.getString("teacher_username");
                            String teacher_password=rs.getString("teacher_password");
                            int teacher_mobile_no=rs.getInt("teacher_mobile_no");
                            
                           Teacher_information teacher_obj=new Teacher_information(teacher_identityno,teacher_name,teacher_post,teacher_salary,teacher_username,teacher_password,teacher_mobile_no);
                        }   
                    } 
                 }
                 catch(Exception E)
                 {
                    System.out.println(E);
                 }
            }       
        });
        attendance.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
                 try
                {
                  Class.forName(JDBC_DRIVER).newInstance();
                    conn=DriverManager.getConnection(DB_URL,USER,PASS); 
                    stmt=conn.createStatement();
                    String sql;

                    sql="select teacher_id,count(attendance_id) from Teacher_Attendance";
                    ResultSet rs=stmt.executeQuery(sql);
                    while(rs.next())
                    {
                        int teacher_identityno=rs.getInt("teacher_id");
                        if(teacher_identityno==teacher_id)
                        {
                            size=rs.getInt("count(attendance_id)");
                            System.out.println("Count is:"+size);                
                        }
                
                    }
                    attendance_id=new int[size];
                    date=new Date[size];
                    status=new String[size];

                    sql=" select attendance_id,t.teacher_id,attendance_date,status from Teacher_Attendance join Teacher as t on t.teacher_id=Teacher_Attendance.teacher_id";
                
                    rs=stmt.executeQuery(sql);
        
                    int i=0;
                    while(rs.next())
                    {
                        int teacher_identityno=rs.getInt("t.teacher_id");
                        if(teacher_identityno==teacher_id)
                        {
                            frame.setVisible(false);
                            attendance_id[i]=rs.getInt("attendance_id");
                            date[i]=rs.getDate("attendance_date");
                            status[i]=rs.getString("status");

                            Teacher_Attendance t_aobj=new Teacher_Attendance(teacher_identityno,attendance_id,date,status);
                        }   
                    }
                 }
                 catch(Exception E)
                 {
                    System.out.println(E);
                 }

            }    

        });
        update.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                frame1=new JFrame("Update");

                update_panel=new JPanel();
                update_panel.setBounds(0,0,800,200);
                update_panel.setBackground(Color.BLUE);
                update_panel.setLayout(null);

                update_label=new JLabel();
                update_label.setForeground(Color.WHITE);
                update_label.setText("Update Mark");
                update_label.setFont(new Font("Serif",Font.BOLD,30));
                update_label.setBounds(250,90,350,50);

                rollno_label=new JLabel();
                rollno_label.setForeground(Color.BLUE);
                rollno_label.setText("Enter Student Rollno:");
                rollno_label.setFont(new Font("Serif",Font.BOLD,25));
                rollno_label.setBounds(10,250,350,30);

                rollno_text=new JTextField();
                rollno_text.setFont(new Font("Serif",Font.BOLD,25));
                rollno_text.setBounds(400,250,200,30);
                rollno_text.setBackground(Color.WHITE);
                rollno_text.setForeground(Color.BLUE);

                subject_id_label=new JLabel();
                subject_id_label.setForeground(Color.BLUE);
                subject_id_label.setText("Enter Subject id    :");
                subject_id_label.setFont(new Font("Serif",Font.BOLD,25));
                subject_id_label.setBounds(10,300,350,30);

                subject_id_text=new JTextField();
                subject_id_text.setFont(new Font("Serif",Font.BOLD,25));
                subject_id_text.setBounds(400,300,200,30);
                subject_id_text.setBackground(Color.WHITE);
                subject_id_text.setForeground(Color.BLUE);

                mark_label=new JLabel();
                mark_label.setForeground(Color.BLUE);
                mark_label.setText("Enter Subject Mark:");
                mark_label.setFont(new Font("Serif",Font.BOLD,25));
                mark_label.setBounds(10,350,350,30);

                mark_text=new JTextField();
                mark_text.setFont(new Font("Serif",Font.BOLD,25));
                mark_text.setBounds(400,350,200,30);
                mark_text.setBackground(Color.WHITE);
                mark_text.setForeground(Color.BLUE);

                submit=new JButton();
                submit.setText("Submit");
                submit.setFont(new Font("Serif",Font.BOLD,20));
                submit.setBackground(Color.BLUE);
                submit.setForeground(Color.WHITE);
                submit.setBounds(350,450,150,50);

                update_panel.add(update_label);
                frame1.add(update_panel);
                frame1.add(rollno_label);
                frame1.add(rollno_text);
                frame1.add(subject_id_label);
                frame1.add(subject_id_text);
                frame1.add(mark_label);
                frame1.add(mark_text);
                frame1.add(submit);
                
                frame1.setSize(800,600);
                frame1.setLayout(null);
                frame1.setVisible(true);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.getContentPane().setBackground(Color.WHITE) ;
                        
                submit.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        rollno=Integer.parseInt(rollno_text.getText());
                        subject_id=Integer.parseInt(subject_id_text.getText());
                        new_mark=Integer.parseInt(mark_text.getText());
                        try
                        {
                          Class.forName(JDBC_DRIVER).newInstance();
                          conn=DriverManager.getConnection(DB_URL,USER,PASS);
                          stmt=conn.createStatement();
                          String sql;
                           ResultSet rs=null;

                            sql=String.format("Update Mark set mark=%d where Rollno=%d AND subject_id=%d",new_mark,rollno,subject_id);
                            ps=conn.prepareStatement(sql);
                            int count=ps.executeUpdate(sql);
                            if(count>0)
                            {
                                frame1.setVisible(false);
                                success_label=new JLabel();
                                success_label.setText("Updated Successfully");
                                success_label.setFont(new Font("Serif",Font.BOLD,25));
                                success_label.setForeground(Color.BLUE);
                                success_label.setBounds(100,550,500,30);
                                
                                frame1.add(success_label);
                                frame1.setVisible(true);
                            }
                
                         }
                         catch(Exception E)
                         {
                            System.out.println(E);
                         }
         
                    }       
                });
         
            }
        });
        delete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               frame.setVisible(false);
               frame1=new JFrame("Delete");

               delete_panel=new JPanel();
               delete_panel.setBounds(0,0,800,200);
               delete_panel.setBackground(Color.BLUE);
               delete_panel.setLayout(null);

               delete_label=new JLabel();
               delete_label.setForeground(Color.WHITE);
               delete_label.setText("Delete Mark");
               delete_label.setFont(new Font("Serif",Font.BOLD,30));
               delete_label.setBounds(250,90,350,50);

               rollno_label=new JLabel();
               rollno_label.setForeground(Color.BLUE);
               rollno_label.setText("Enter Student Rollno:");
               rollno_label.setFont(new Font("Serif",Font.BOLD,25));
               rollno_label.setBounds(10,250,350,30);

               rollno_text=new JTextField();
               rollno_text.setFont(new Font("Serif",Font.BOLD,25));
               rollno_text.setBounds(400,250,200,30);
               rollno_text.setBackground(Color.WHITE);
               rollno_text.setForeground(Color.BLUE);

               subject_id_label=new JLabel();
               subject_id_label.setForeground(Color.BLUE);
               subject_id_label.setText("Enter Subject id    :");
               subject_id_label.setFont(new Font("Serif",Font.BOLD,25));
               subject_id_label.setBounds(10,350,350,30);

               subject_id_text=new JTextField();
               subject_id_text.setFont(new Font("Serif",Font.BOLD,25));
               subject_id_text.setBounds(400,350,200,30);
               subject_id_text.setBackground(Color.WHITE);
               subject_id_text.setForeground(Color.BLUE);

               
               submit=new JButton();
               submit.setText("Submit");
               submit.setFont(new Font("Serif",Font.BOLD,20));
               submit.setBackground(Color.BLUE);
               submit.setForeground(Color.WHITE);
               submit.setBounds(350,450,150,50);
               
               delete_panel.add(delete_label);
               frame1.add(delete_panel);
               frame1.add(rollno_label);
               frame1.add(rollno_text);
               frame1.add(subject_id_label);
               frame1.add(subject_id_text);
               frame1.add(submit);

               frame1.setSize(800,600);
               frame1.setLayout(null);
               frame1.setVisible(true);
               frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame1.getContentPane().setBackground(Color.WHITE) ;

               submit.addActionListener(new ActionListener()
               {
                   public void actionPerformed(ActionEvent e)
                   {
                       rollno=Integer.parseInt(rollno_text.getText());
                       subject_id=Integer.parseInt(subject_id_text.getText());
                       try
                       {
                         Class.forName(JDBC_DRIVER).newInstance();
                         conn=DriverManager.getConnection(DB_URL,USER,PASS);
                         stmt=conn.createStatement();
                         String sql;
                          ResultSet rs=null;

                           sql=String.format("Delete from Mark where Rollno=%d AND subject_id=%d",rollno,subject_id);
                           ps=conn.prepareStatement(sql);
                           int count=ps.executeUpdate(sql);

                           if(count>0)
                           {
                               frame1.setVisible(false);
                               success_label=new JLabel();
                               success_label.setText("Deleted Successfully");
                               success_label.setFont(new Font("Serif",Font.BOLD,25));
                               success_label.setForeground(Color.BLUE);
                               success_label.setBounds(100,550,500,30);
                               
                               frame1.add(success_label);
                               frame1.setVisible(true);
                           }
               
                        }
                        catch(Exception E)
                        {
                           System.out.println(E);
                        }
        
                   }       
               });
            }
        });
        
    }
}
class Teacher_information extends JFrame
{
   JFrame frame;
   JButton previous;
   JLabel id,name,post,salary,username;
   JLabel password,mobile_no;
   JLabel t_id,t_name,t_post,t_salary,t_username;
   JLabel t_password,t_mobile_no;
   
   Teacher_information(int teacher_id,String teacher_name,String teacher_post,String teacher_salary,String teacher_username,String teacher_password,int teacher_mobile_no)
   {
       frame=new JFrame("Teacher Information");

       id=new JLabel("id             :");
       id.setFont(new Font("Serif",Font.BOLD,20));
       id.setBounds(20,50,200,30);
       id.setForeground(Color.WHITE);

       t_id=new JLabel();
       t_id.setText(""+teacher_id);
       t_id.setFont(new Font("Serif",Font.BOLD,20));
       t_id.setBounds(220,50,600,30);
       t_id.setForeground(Color.WHITE);

       name=new JLabel("Name       :");
       name.setFont(new Font("Serif",Font.BOLD,20));
       name.setBounds(20,110,200,30);
       name.setForeground(Color.WHITE);

       t_name=new JLabel();
       t_name.setText(""+teacher_name);
       t_name.setFont(new Font("Serif",Font.BOLD,20));
       t_name.setBounds(220,110,600,30);
       t_name.setForeground(Color.WHITE);

       post=new JLabel("Post         :");
       post.setFont(new Font("Serif",Font.BOLD,20));
       post.setBounds(20,170,200,30);
       post.setForeground(Color.WHITE);

       t_post=new JLabel();
       t_post.setText(""+teacher_post);
       t_post.setFont(new Font("Serif",Font.BOLD,20));
       t_post.setBounds(220,170,600,30);
       t_post.setForeground(Color.WHITE);

       salary=new JLabel("Salary      :");
       salary.setFont(new Font("Serif",Font.BOLD,20));
       salary.setBounds(20,230,200,30);
       salary.setForeground(Color.WHITE);

       t_salary=new JLabel();
       t_salary.setText(""+teacher_salary);
       t_salary.setFont(new Font("Serif",Font.BOLD,20));
       t_salary.setBounds(220,230,600,30);
       t_salary.setForeground(Color.WHITE);

       username=new JLabel("Username:");
       username.setFont(new Font("Serif",Font.BOLD,20));
       username.setBounds(20,290,200,30);
       username.setForeground(Color.WHITE);

       t_username=new JLabel();
       t_username.setText(""+teacher_username);
       t_username.setFont(new Font("Serif",Font.BOLD,20));
       t_username.setBounds(220,290,600,30);
       t_username.setForeground(Color.WHITE);

       password=new JLabel("Password :");
       password.setFont(new Font("Serif",Font.BOLD,20));
       password.setBounds(20,350,200,30);
       password.setForeground(Color.WHITE);

       t_password=new JLabel();
       t_password.setText(""+teacher_password);
       t_password.setFont(new Font("Serif",Font.BOLD,20));
       t_password.setBounds(220,350,600,30);
       t_password.setForeground(Color.WHITE);


       mobile_no=new JLabel("MobileNo :");
       mobile_no.setFont(new Font("Serif",Font.BOLD,20));
       mobile_no.setBounds(20,410,200,30);
       mobile_no.setForeground(Color.WHITE);

       t_mobile_no=new JLabel();
       t_mobile_no.setText(""+teacher_mobile_no);
       t_mobile_no.setFont(new Font("Serif",Font.BOLD,20));
       t_mobile_no.setBounds(220,410,600,30);
       t_mobile_no.setForeground(Color.WHITE);

       previous=new JButton("Previous");
       previous.setText("Previous");
       previous.setFont(new Font("Serif",Font.BOLD,15));
       previous.setBackground(Color.white);
       previous.setForeground(Color.BLUE);
       previous.setBounds(450,500,130,40);

       frame.add(id);
       frame.add(t_id);
       frame.add(name);
       frame.add(t_name);
       frame.add(post);
       frame.add(t_post);
       frame.add(salary);
       frame.add(t_salary);
       frame.add(username);
       frame.add(t_username);
       frame.add(password);
       frame.add(t_password);
       frame.add(mobile_no);
       frame.add(t_mobile_no);
       frame.add(previous);

        frame.setSize(600,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLUE) ;

        previous.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.setVisible(false);
                frame.dispose();
                Teacher_operations tobj=new Teacher_operations(teacher_name,teacher_id);
            }
        });
   }   
}
class Teacher_Attendance extends JFrame
{
    JFrame frame;
    JLabel teacher_id_label,teacher_id_number,attendance_id_label,attendance_date_label,status_label;

    Teacher_Attendance(int teacher_id,int attendance_id[],Date attendance_date[],String status[])
    {
        JFrame frame1;
        int size=attendance_id.length;        

        JLabel att_id[]=new JLabel[size];
        JLabel att_date[]=new JLabel[size];
        JLabel att_status[]=new JLabel[size];

        frame1=new JFrame("Attendance");
       
        teacher_id_label=new JLabel("teacher_id");
        teacher_id_label.setBounds(10,10,150,30);
        teacher_id_label.setForeground(Color.WHITE);
        teacher_id_label.setFont(new Font("Serif",Font.BOLD,20));

        teacher_id_number=new JLabel();
        teacher_id_number.setText(""+teacher_id);
        teacher_id_number.setBounds(200,10,100,30);
        teacher_id_number.setForeground(Color.WHITE);
        teacher_id_number.setFont(new Font("Serif",Font.BOLD,20));
         
        attendance_id_label=new JLabel();
        attendance_id_label.setText("Attendance_id");
        attendance_id_label.setForeground(Color.WHITE);
        attendance_id_label.setFont(new Font("Serif",Font.BOLD,20));
        attendance_id_label.setBounds(50,40,200,30);
      
        attendance_date_label=new JLabel();
        attendance_date_label.setText("Attendance_date");
        attendance_date_label.setForeground(Color.WHITE);
        attendance_date_label.setFont(new Font("Serif",Font.BOLD,20));
        attendance_date_label.setBounds(300,40,200,30);
      
        status_label=new JLabel();
        status_label.setText("Status");
        status_label.setForeground(Color.WHITE);
        status_label.setFont(new Font("Serif",Font.BOLD,20));
        status_label.setBounds(550,40,200,30);
      
        int y=80,j=0;
        for(j=0;j<size;j++)
        { 
            att_id[j]=new JLabel();
            att_id[j].setText(""+attendance_id[j]);
            att_id[j].setForeground(Color.WHITE);
            att_id[j].setFont(new Font("Serif",Font.BOLD,20));
            att_id[j].setBounds(50,y,200,30);
            
            att_date[j]=new JLabel();
            att_date[j].setText(""+attendance_date[j]);
            att_date[j].setForeground(Color.WHITE);
            att_date[j].setFont(new Font("Serif",Font.BOLD,20));
            att_date[j].setBounds(300,y,200,30);
            
            att_status[j]=new JLabel();
            att_status[j].setText(""+status[j]);
            att_status[j].setForeground(Color.WHITE);
            att_status[j].setFont(new Font("Serif",Font.BOLD,20));
            att_status[j].setBounds(550,y,150,30);
            y=y+20;
        }
        frame1.add(teacher_id_label);
        frame1.add(teacher_id_number);
        frame1.add(attendance_id_label);
        frame1.add(attendance_date_label);
        frame1.add(status_label);
        for(j=0;j<size;j++)
       {
          frame1.add(att_id[j]);
          frame1.add(att_date[j]);
          frame1.add(att_status[j]);
        }
        frame1.setSize(800,800);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.BLUE) ;               
    }
}
class Student extends JFrame
{
    JFrame frame;
    JPanel welcome_panel;
    JLabel welcome_label;
    
    JLabel username_label,password_label,wrong_uname_pass,wrong_pass,wrong_uname;
    JTextField username;
    JPasswordField password;
    JButton submit;
    String uname;
    String pass;
    String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
        
    String DB_URL="jdbc:mysql://localhost/Computer_Department";
 
    String USER ="root";
    String PASS ="";

    Connection conn=null;
    Statement stmt=null;

    Student()
    {
        frame=new JFrame("Student Login");
        frame.setSize(600,600);
      
        welcome_panel=new JPanel();
        welcome_panel.setBounds(0,0,600,200);
        welcome_panel.setLayout(null);
        welcome_panel.setBackground(Color.BLUE);

        welcome_label=new JLabel();
        welcome_label.setText("Welcome in Student Login");
        welcome_label.setBounds(10,90,600,50);
        welcome_label.setForeground(Color.WHITE);
        welcome_label.setFont(new Font("Serif",Font.BOLD,30));
       
        username_label=new JLabel("Username:");
        username_label.setForeground(Color.BLUE);
        username_label.setBounds(30,300,100,30);
        username_label.setFont(new Font("Serif",Font.BOLD,16));

        username=new JTextField();
        username.setToolTipText("Enter your Username");
        username.setFont(new Font("Monospaced",Font.BOLD,15));
        username.setForeground(Color.BLUE);
        username.setBounds(200,300,200,30);

        password_label=new JLabel("Password:");
        password_label.setForeground(Color.BLUE);
        password_label.setBounds(30,400,100,30);
        password_label.setFont(new Font("Serif",Font.BOLD,16));

        password=new JPasswordField();
        password.setToolTipText("Enter your Password");
        password.setForeground(Color.BLUE);
        password.setBounds(200,400,200,30);

        submit=new JButton("Submit");
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.WHITE);
        submit.setBounds(100,500,150,30);
        
        JButton previous=new JButton("Previous");
        previous.setBackground(Color.BLUE);
        previous.setForeground(Color.WHITE);
        previous.setBounds(300,500,150,30);

        welcome_panel.add(welcome_label);
        frame.add(welcome_panel);
        frame.add(username_label);
        frame.add(username);
        frame.add(password_label);
        frame.add(password);
        frame.add(submit);
        frame.add(previous);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);

        previous.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.setVisible(false);
                frame.dispose();
                School_Management_System obj=new School_Management_System();
            }
        });
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                uname=username.getText();
                pass=new String(password.getPassword());

                try
                {
                  Class.forName(JDBC_DRIVER).newInstance();
        
                  
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
        
                    
                    stmt=conn.createStatement();
                    String sql;
        
                    sql="Select student_username,student_password,student_name,Rollno from Student";
                    ResultSet rs=stmt.executeQuery(sql);
        
                    while(rs.next())
                    {
                        
        
                        String student_username=rs.getString("student_username");
                        String student_password=rs.getString("student_password");
                        String student_name=rs.getString("student_name");
                        int Rollno=rs.getInt("Rollno");

                        if((student_username.equals(uname))&&(student_password.equals(pass)))
                        {
                            frame.setVisible(false);
                           Student_operations studentobj=new Student_operations(student_name,Rollno);
                        }
                    }
        
                   
                 }
                 catch(Exception E)
                 {
                    System.out.println(E);
                 }
 
            }       
        });
    }
}
class Student_operations extends JFrame
{
    JFrame frame;
    JPanel login_panel;
    JLabel welcome_label,select;
    JButton information,result,attendance;
    
    String uname;
    String pass;
                            
    int attendance_id[];
    Date attendance_date[];
    String subject_name[];
    String status[];
    int mark[];
    int i;
    int size;

    String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
        
    String DB_URL="jdbc:mysql://localhost/Computer_Department";

 
    String USER ="root";
    String PASS ="";

    Connection conn=null;
    Statement stmt=null;

    Student_operations(String student_name,int student_Rollno)
    {
        frame=new JFrame("Student Login");
        frame.setSize(600,600);
        
        login_panel=new JPanel();
        login_panel.setBounds(0,0,600,200);
        login_panel.setLayout(null);
        login_panel.setBackground(Color.BLUE);

        welcome_label=new JLabel();
        welcome_label.setText("Welcome : "+student_name);
        welcome_label.setBounds(10,90,600,50);
        welcome_label.setFont(new Font("Serif",Font.BOLD,30));
        welcome_label.setForeground(Color.WHITE);

        select=new JLabel();
        select.setText("Select what do you want to see:");
        select.setBounds(10,230,600,50);
        select.setFont(new Font("Serif",Font.BOLD,20));
        select.setForeground(Color.BLUE);

        information=new JButton("Your Information");
        information.setText("Your Information");
        information.setFont(new Font("Serif",Font.BOLD,15));
        information.setBounds(200,300,200,50);
        information.setForeground(Color.WHITE);
        information.setBackground(Color.BLUE);

        attendance=new JButton("Your Attendance");
        attendance.setText("Your Attendance");
        attendance.setFont(new Font("Serif",Font.BOLD,15));
        attendance.setBounds(200,400,200,50);
        attendance.setForeground(Color.WHITE);
        attendance.setBackground(Color.BLUE);

        result=new JButton("Your Result");
        result.setText("Your Result");
        result.setFont(new Font("Serif",Font.BOLD,15));
        result.setBounds(200,500,200,50);
        result.setForeground(Color.WHITE);
        result.setBackground(Color.BLUE);

        JButton previous=new JButton("Previous");
        previous.setBackground(Color.BLUE);
        previous.setForeground(Color.WHITE);
        previous.setBounds(450,550,100,30);

        login_panel.add(welcome_label);
        frame.add(login_panel);
        frame.add(select);
        frame.add(information);
        frame.add(attendance);
        frame.add(result);
        frame.add(previous);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE) ;
 
        previous.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.setVisible(false);
                frame.dispose();
                Student obj=new Student();
            }
        });
        information.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                  Class.forName(JDBC_DRIVER).newInstance();
        
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
                    stmt=conn.createStatement();
                    String sql;
        
                    sql="select Rollno,student_name,student_class,student_div,student_username,student_password from Student";
                    ResultSet rs=stmt.executeQuery(sql);
        
                    while(rs.next())
                    {
                        int student_rno=rs.getInt("Rollno");
                        if(student_rno==student_Rollno)
                        {
                            frame.setVisible(false);
                            String student_name=rs.getString("student_name");
                            String student_class=rs.getString("student_class");
                            String student_div=rs.getString("student_div");
                            String student_username=rs.getString("student_username");
                            String student_password=rs.getString("student_password");
                             
                            Student_information s_obj=new Student_information(student_rno,student_name,student_class,student_div,student_username,student_password); 
                      
                        }   
                    }
                 }
                 catch(Exception E)
                 {
                    System.out.println(E);
                 }
 
            }       
        });
        attendance.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                  Class.forName(JDBC_DRIVER).newInstance();          
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
            
                    stmt=conn.createStatement();
                    String sql;
        
                    sql="select Rollno,count(attendance_id) from Student_attendance";
                    ResultSet rs=stmt.executeQuery(sql);
                    while(rs.next())
                    {
                        int student_rno=rs.getInt("Rollno");
                        if(student_rno==student_Rollno)
                        {
                            size=rs.getInt("count(attendance_id)");
                            System.out.println("Count is:"+size);                
                        }
                
                    }
                    subject_name=new String[size];
                    attendance_id=new int[size];
                    attendance_date=new Date[size];
                    status=new String[size];

                    sql="select s.Rollno,sub.subject_name,attendance_id,attendance_date,status from Student_attendance as a join Student as s on s.Rollno=a.Rollno join Subject as sub on sub.subject_id=a.subject_id";
                    rs=stmt.executeQuery(sql);
        
                    i=0;
                    while(rs.next())
                    {
                        int student_rno=rs.getInt("s.Rollno");
                        if(student_rno==student_Rollno)
                        {
                            subject_name[i]=rs.getString("subject_name");
                            attendance_id[i]=rs.getInt("attendance_id");
                            attendance_date[i]=rs.getDate("attendance_date");
                            status[i]=rs.getString("status");   
                          i++;                        
                        }
                    }
                    Student_Attendance sobj=new Student_Attendance(student_Rollno,subject_name,attendance_id,attendance_date,status); 
                 }
                 catch(Exception E)
                 {
                    System.out.println(E);
                 }
            }       
        });
        result.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                  Class.forName(JDBC_DRIVER).newInstance();
                    conn=DriverManager.getConnection(DB_URL,USER,PASS);
                    stmt=conn.createStatement();
                    String sql;
                    sql="select Rollno,count(subject_id) from Mark";
                    ResultSet rs=stmt.executeQuery(sql);
                    while(rs.next())
                    {
                        int student_rno=rs.getInt("Rollno");
                        if(student_rno==student_Rollno)
                        {
                            size=rs.getInt("count(subject_id)");
                            System.out.println("Count is:"+size);                
                        }
                    }
                    subject_name=new String[size];
                    mark=new int[size];

                    sql="select s.Rollno,s.student_name,sub.subject_name,m.mark from Mark as m join Student as s on s.Rollno=m.Rollno join Subject as sub on sub.subject_id=m.subject_id";
                    rs=stmt.executeQuery(sql);
        
                    int i=0;
                    while(rs.next())
                    {
                        int student_rno=rs.getInt("s.Rollno");
                        if(student_rno==student_Rollno)
                        {
                            subject_name[i]=rs.getString("sub.subject_name");
                            mark[i]=rs.getInt("m.mark");
                            i++;           
                        }
                    }
                    Student_Result srobj=new Student_Result(student_Rollno,subject_name,mark);
                 }
                 catch(Exception E)
                 {
                    System.out.println(E);
                 }
 
            }       
        });
    }
}
class Student_information extends JFrame
{
    JFrame frame;
    JLabel rno,name,st_class,div,username,password;
    JLabel s_rno,s_name,s_class,s_div,s_username,s_password;
    //student_rno,student_name,student_class,student_div,student_username,student_password
    Student_information(int student_rno,String student_name,String student_class,String student_div,String student_username,String student_password)
    {
        frame=new JFrame("Student Information");

        rno=new JLabel("Your Rollno       :");
        rno.setFont(new Font("Serif",Font.BOLD,20));
        rno.setBounds(20,50,200,30);
        rno.setForeground(Color.WHITE);
 
        s_rno=new JLabel();
        s_rno.setText(""+student_rno);
        s_rno.setFont(new Font("Serif",Font.BOLD,20));
        s_rno.setBounds(220,50,600,30);
        s_rno.setForeground(Color.WHITE);
 
        name=new JLabel("Your Name       :");
        name.setFont(new Font("Serif",Font.BOLD,20));
        name.setBounds(20,110,200,30);
        name.setForeground(Color.WHITE);
 
        s_name=new JLabel();
        s_name.setText(""+student_name);
        s_name.setFont(new Font("Serif",Font.BOLD,20));
        s_name.setBounds(220,110,600,30);
        s_name.setForeground(Color.WHITE);
 
        st_class=new JLabel("Your Class        :");
        st_class.setFont(new Font("Serif",Font.BOLD,20));
        st_class.setBounds(20,170,200,30);
        st_class.setForeground(Color.WHITE);
 
        s_class=new JLabel();
        s_class.setText(""+student_class);
        s_class.setFont(new Font("Serif",Font.BOLD,20));
        s_class.setBounds(220,170,600,30);
        s_class.setForeground(Color.WHITE);
 
        div=new JLabel("Your Division    :");
        div.setFont(new Font("Serif",Font.BOLD,20));
        div.setBounds(20,230,200,30);
        div.setForeground(Color.WHITE);
 
        s_div=new JLabel();
        s_div.setText(""+student_div);
        s_div.setFont(new Font("Serif",Font.BOLD,20));
        s_div.setBounds(220,230,600,30);
        s_div.setForeground(Color.WHITE);
 
        username=new JLabel("Your Username:");
        username.setFont(new Font("Serif",Font.BOLD,20));
        username.setBounds(20,290,200,30);
        username.setForeground(Color.WHITE);
 
        s_username=new JLabel();
        s_username.setText(""+student_username);
        s_username.setFont(new Font("Serif",Font.BOLD,20));
        s_username.setBounds(220,290,600,30);
        s_username.setForeground(Color.WHITE);
 
        password=new JLabel("Your Password :");
        password.setFont(new Font("Serif",Font.BOLD,20));
        password.setBounds(20,350,200,30);
        password.setForeground(Color.WHITE);
 
        s_password=new JLabel();
        s_password.setText(""+student_password);
        s_password.setFont(new Font("Serif",Font.BOLD,20));
        s_password.setBounds(220,350,600,30);
        s_password.setForeground(Color.WHITE);
 
        JButton previous=new JButton("Previous");
        previous.setBackground(Color.white);
        previous.setForeground(Color.BLUE); 
        previous.setBounds(450,500,120,30);

        frame.add(rno);
        frame.add(s_rno);
        frame.add(name);
        frame.add(s_name);
        frame.add(st_class);
        frame.add(s_class);
        frame.add(div);
        frame.add(s_div);
        frame.add(username);
        frame.add(s_username);
        frame.add(password);
        frame.add(s_password);
        frame.add(previous);
        frame.setSize(600,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLUE) ;

        previous.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.setVisible(false);
                frame.dispose();
                Student_operations sobj=new Student_operations(student_name,student_rno);
            }
        });
 
    }

}
class Student_Attendance extends JFrame
{
    JFrame frame1;
    JLabel Rollno,student_roll_number,subject_name_label,attendance_id_label,attendance_date_label,status_label;
       
    Student_Attendance(int student_Rollno,String subject_name[],int attendance_id[],Date attendance_date[],String status[])
    {
        int size=attendance_id.length;

        JLabel sub_name[]=new JLabel[size];
        JLabel att_id[]=new JLabel[size];
        JLabel att_date[]=new JLabel[size];
        JLabel att_status[]=new JLabel[size];

        frame1=new JFrame("Attendance");
        
        Rollno=new JLabel("Rollno");
        Rollno.setBounds(10,10,100,30);
        Rollno.setForeground(Color.WHITE);
        Rollno.setFont(new Font("Serif",Font.BOLD,20));

        student_roll_number=new JLabel();
        student_roll_number.setText(""+student_Rollno);
        student_roll_number.setBounds(150,10,100,30);
        student_roll_number.setForeground(Color.WHITE);
        student_roll_number.setFont(new Font("Serif",Font.BOLD,20));
        
        subject_name_label=new JLabel();
        subject_name_label.setText("Subject name");
        subject_name_label.setForeground(Color.WHITE);
        subject_name_label.setFont(new Font("Serif",Font.BOLD,20));
        subject_name_label.setBounds(10,50,190,30);
        
        attendance_id_label=new JLabel();
        attendance_id_label.setText("Attendance_id");
        attendance_id_label.setForeground(Color.WHITE);
        attendance_id_label.setFont(new Font("Serif",Font.BOLD,20));
        attendance_id_label.setBounds(200,50,200,30);
      
        attendance_date_label=new JLabel();
        attendance_date_label.setText("Attendance_date");
        attendance_date_label.setForeground(Color.WHITE);
        attendance_date_label.setFont(new Font("Serif",Font.BOLD,20));
        attendance_date_label.setBounds(410,50,200,30);
      
        status_label=new JLabel();
        status_label.setText("Status");
        status_label.setForeground(Color.WHITE);
        status_label.setFont(new Font("Serif",Font.BOLD,20));
        status_label.setBounds(630,50,200,30);
      
        int y=80,j=0;

        for(j=0;j<size;j++)
        {
            
            sub_name[j]=new JLabel();
            sub_name[j].setText(""+subject_name[j]);
            sub_name[j].setForeground(Color.WHITE);
            sub_name[j].setFont(new Font("Serif",Font.BOLD,20));
            sub_name[j].setBounds(10,y,150,30);
            
            att_id[j]=new JLabel();
            att_id[j].setText(""+attendance_id[j]);
            att_id[j].setForeground(Color.WHITE);
            att_id[j].setFont(new Font("Serif",Font.BOLD,20));
            att_id[j].setBounds(200,y,200,30);
            
            att_date[j]=new JLabel();
            att_date[j].setText(""+attendance_date[j]);
            att_date[j].setForeground(Color.WHITE);
            att_date[j].setFont(new Font("Serif",Font.BOLD,20));
            att_date[j].setBounds(410,y,200,30);
            
            att_status[j]=new JLabel();
            att_status[j].setText(""+status[j]);
            att_status[j].setForeground(Color.WHITE);
            att_status[j].setFont(new Font("Serif",Font.BOLD,20));
            att_status[j].setBounds(630,y,150,30);

            y=y+20;
            
        }


        frame1.add(Rollno);
        frame1.add(student_roll_number);
        frame1.add(subject_name_label);
        frame1.add(attendance_id_label);
        frame1.add(attendance_date_label);
        frame1.add(status_label);

        for(j=0;j<size;j++)
       {
          frame1.add(sub_name[j]);
          frame1.add(att_id[j]);
          frame1.add(att_date[j]);
          frame1.add(att_status[j]);
        }
        frame1.setSize(800,800);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.BLUE) ;
    }
}
class Student_Result extends JFrame
{
    JFrame frame1;
    JLabel Rollno,subject_name_label,mark_label,student_roll_number;
    
    Student_Result(int student_Rollno,String subject_name[],int mark[])
    {
        int size=subject_name.length;
    
        JLabel sub_name[]=new JLabel[size];
        JLabel sub_mark[]=new JLabel[size];
    
        frame1=new JFrame();

        Rollno=new JLabel("Rollno:");
        Rollno.setBounds(10,10,100,30);
        Rollno.setForeground(Color.WHITE);
        Rollno.setFont(new Font("Serif",Font.BOLD,20));

        student_roll_number=new JLabel();
        student_roll_number.setText(""+student_Rollno);
        student_roll_number.setBounds(150,10,100,30);
        student_roll_number.setForeground(Color.WHITE);
        student_roll_number.setFont(new Font("Serif",Font.BOLD,20));
       
        subject_name_label=new JLabel();
        subject_name_label.setText("Subject name");
        subject_name_label.setForeground(Color.WHITE);
        subject_name_label.setFont(new Font("Serif",Font.BOLD,20));
        subject_name_label.setBounds(10,50,190,30);
       
        mark_label=new JLabel();
        mark_label.setText("Mark");
        mark_label.setForeground(Color.WHITE);
        mark_label.setFont(new Font("Serif",Font.BOLD,20));
        mark_label.setBounds(250,50,190,30);
 
        int y=80,j=0;

        for(j=0;j<size;j++)
        {
            sub_name[j]=new JLabel();
            sub_name[j].setText(""+subject_name[j]);
            sub_name[j].setForeground(Color.WHITE);
            sub_name[j].setFont(new Font("Serif",Font.BOLD,20));
            sub_name[j].setBounds(10,y,150,30);
            
            sub_mark[j]=new JLabel();
            sub_mark[j].setText(""+mark[j]);
            sub_mark[j].setForeground(Color.WHITE);
            sub_mark[j].setFont(new Font("Serif",Font.BOLD,20));
            sub_mark[j].setBounds(250,y,200,30);

            y=y+20;
            
        }
        frame1.add(Rollno);
        frame1.add(student_roll_number);
        frame1.add(subject_name_label);
        frame1.add(mark_label);

        for(j=0;j<size;j++)
        {
           frame1.add(sub_name[j]);
           frame1.add(sub_mark[j]);
        }
        
        frame1.setSize(600,600);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setBackground(Color.BLUE) ;
    }  
}
public class School_Management_System extends JFrame
{
    JFrame frame;
    JLabel welcome,login_credential;
    JPanel welcome_panel;
    JButton admin_login,teacher_login,student_login;
    School_Management_System()
    {

        frame=new JFrame("Main Frame");
 
        welcome_panel=new JPanel();
        welcome_panel.setBackground(Color.BLUE);
        welcome_panel.setBounds(0,0,600,200);

        welcome=new JLabel("SCHOOL MANAGEMENT SYSTEM");
        welcome.setBounds(10,90,600,50);
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Serif",Font.BOLD,30));

        admin_login=new JButton("Admin login");
        admin_login.setText("Admin login");
        admin_login.setFont(new Font("Serif",Font.BOLD,20));
        admin_login.setBackground(Color.BLUE);
        admin_login.setForeground(Color.WHITE);
        admin_login.setBounds(200,300,200,50);

        teacher_login=new JButton("Teacher login");
        teacher_login.setText("Teacher login");
        teacher_login.setFont(new Font("Serif",Font.BOLD,20));
        teacher_login.setBackground(Color.BLUE);
        teacher_login.setForeground(Color.WHITE);
        teacher_login.setBounds(200,400,200,50);

        student_login=new JButton("Student login");
        student_login.setText("Student login");
        student_login.setFont(new Font("Serif",Font.BOLD,20));
        student_login.setBackground(Color.BLUE);
        student_login.setForeground(Color.WHITE);
        student_login.setBounds(200,500,200,50);

        login_credential=new JLabel("Select your login credential:");
        login_credential.setFont(new Font("Serif",Font.BOLD,20));
        login_credential.setForeground(Color.BLUE);
        login_credential.setBounds(10,210,600,60);

        
        welcome_panel.add(welcome);
        welcome_panel.setLayout(null);

        frame.add(admin_login);
        frame.add(teacher_login);
        frame.add(student_login);
        frame.add(login_credential);


        frame.add(welcome_panel);
        frame.setSize(600,600);
        
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);

        admin_login.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.setVisible(false);
                Admin aobj=new Admin();
            }
        });
        teacher_login.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.setVisible(false);
                Teacher aobj=new Teacher();
            }
        });
    
        student_login.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                frame.setVisible(false);
                Student aobj=new Student();
            }
        });
    
    }
    public static void main(String args[])
    {
        School_Management_System cobj=new School_Management_System();
    }
}
