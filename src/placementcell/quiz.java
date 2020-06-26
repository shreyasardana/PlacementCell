/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package placementcell;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import static sun.swing.SwingUtilities2.submit;

/**
 *
 * @author dell
 */
public class quiz extends javax.swing.JFrame {
    
    int rno,id;
    ArrayList<csquiz> ldata=new ArrayList<>();
    
  public quiz()
  {
      
      initComponents();
      display();
  }
    /**
     * Creates new form quiz
     */
    public quiz(int a,int b) {
        initComponents();
         getContentPane().setBackground(Color.BLUE);
        
        rno=a;
        id=b;
        
        display();
    }
    public void display()
    {
        try
        {
            String q="select * from tbquestion where companyid=? limit 5";
            myconnection obj=new myconnection();
            PreparedStatement pst=obj.db.prepareStatement(q);
            pst.setInt(1,id);
            ResultSet rs=pst.executeQuery();
            JRadioButton option1=null,option2=null,option3=null,option4=null;
            JLabel lop1=null;
            int x=10,y=20,w=400,h=40;
            ButtonGroup b=null;
            while(rs.next())
            {
                b=new ButtonGroup();
                csquiz c=new csquiz();
                c.setQid(rs.getInt("id"));
                c.setQname(rs.getString("qname"));
                c.setOption1(rs.getString("option1"));
                c.setOption2(rs.getString("option2"));
                c.setOption3(rs.getString("option3"));
                c.setOption4(rs.getString("option4"));
                c.setCanswer(rs.getInt("canswer"));
                c.setUseranswer(-1);
                option1=new JRadioButton(c.getOption1());
                option2=new JRadioButton(c.getOption2());
                option3=new JRadioButton(c.getOption3());
                option4=new JRadioButton(c.getOption4());
                lop1=new JLabel();
                lop1.setText(c.getQname());
                lop1.setBounds(x,y,w,h);
                y=y+20;
                option1.setBounds(x,y,w,h);
                y=y+20;
                option2.setBounds(x,y,w,h);
                y=y+20;
                option3.setBounds(x,y,w,h);
                y=y+20;
                option4.setBounds(x,y,w,h);
                y=y+20;
                b.add(option1);
                b.add(option2);
                b.add(option3);
                b.add(option4);
                add(lop1);
                add(option1);
                add(option2);
                add(option3);
                add(option4);
                
                ldata.add(c);
               
                option1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.setUseranswer(1);
                    }
                });
                option2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.setUseranswer(2);
                    }
                });
                option3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.setUseranswer(3);
                    }
                });
                option4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.setUseranswer(4);
                    }
                });
                 }
            JButton bsubmit=new JButton("SUBMIT");
            bsubmit.setBounds(x,y+40,w,h);
            add(bsubmit);
            bsubmit.addActionListener(new ActionListener(){
          
            public void actionPerformed(ActionEvent e){
                int c=0,w1=0,u=0;
                Date d=new Date();
                for(int i=0;i<ldata.size();i++)
                {
                    csquiz ca=null;
                    ca=(csquiz)ldata.get(i);
                    try
                    {
                        myconnection obj1=new myconnection();
                        String q1="insert into tbresult(rno,qid,companyid,useranswer,canswer,examdate)values(?,?,?,?,?,?)";
                        PreparedStatement pst1=obj1.db.prepareStatement(q1);
                        pst1.setInt(1,rno);
                        pst1.setInt(2,ca.getQid());
                        pst1.setInt(3,id);
                        pst1.setInt(4,ca.getUseranswer());
                        pst1.setInt(5,ca.getCanswer());
                        pst1.setString(6,""+d);
                        pst1.executeUpdate();
                        if(ca.getUseranswer()==ca.getCanswer())
                        {
                            c++;
                        }
                        else if(ca.getUseranswer()!=ca.getCanswer()&&ca.getUseranswer()!=-1)
                        {
                            w1++;
                        }
                        else if(ca.getUseranswer()==-1)
                        {
                            u++;
                        }
                        }
                    catch(Exception dead)
                    {
                        System.out.println(dead.getMessage());
                    }
                    System.out.println("Qname is"+ca.getQname()+"answer is"+ca.getCanswer());
                }
                try
                {
                    String s="insert into tbaggregate(rno,companyid,correct_answers,incorrect_answers,unattempted,testdate)values(?,?,?,?,?,?)";
                     PreparedStatement pst1=obj.db.prepareStatement(s);
                        pst1.setInt(1,rno);
                        pst1.setInt(2,id);
                        pst1.setInt(3,c);
                        pst1.setInt(4,w1);
                        pst1.setInt(5,u);
                        pst1.setString(6,""+d);
                        pst1.executeUpdate();
                }
                catch(Exception e1)
                {
                    System.out.println(e1.getMessage());
                }
               
            }
            
        });
            
            
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new quiz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
