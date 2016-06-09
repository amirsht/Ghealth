package client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class ShowWeeklyReports extends JFrame {

    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JTable table;
    private JTable table2;
    private String [][] str;

    public ShowWeeklyReports(String [][] str) {
    	this.str=str;
    	super.setTitle("Weekly Report");
    	initComponents2();
    	setVisible(true);
    	//initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table = new javax.swing.JTable() {

            @Override
            public Component prepareRenderer(
                TableCellRenderer renderer, int row, int col) {
                if (col == 0) {
                    return this.getTableHeader().getDefaultRenderer()
                        .getTableCellRendererComponent(this, this.getValueAt(
                            row, col), false, false, row, col);
                } else {
                    return super.prepareRenderer(renderer, row, col);
                }
            }
        };
        table.setAutoCreateRowSorter(false);
        table.setPreferredScrollableViewportSize(new Dimension(600, 200));
        final JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(table));
        String [][] weekSum = getWeeklySummery();
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]
            		{
                {"Average", weekSum[0][0], weekSum[0][1],weekSum[0][2], weekSum[0][3]},
                {"Minimum", weekSum[1][0], weekSum[1][1], weekSum[1][2], weekSum[1][3]},
                {"Maximum", weekSum[2][0], weekSum[2][1], weekSum[2][2], weekSum[2][3] },
                {"SD", weekSum[3][0], weekSum[3][1], weekSum[3][2], weekSum[3][3]}
            },
            new String[]{
                "", "Number of patients", "Patient wait times", "Wait time since Ap date"
            }));
        
        scrollPane = new JScrollPane(table);
        this.add(scrollPane,BorderLayout.SOUTH);
        pack();
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents2() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table2 = new javax.swing.JTable() {

            @Override
            public Component prepareRenderer(
                TableCellRenderer renderer, int row, int col) {
                if (col == 0) {
                    return this.getTableHeader().getDefaultRenderer()
                        .getTableCellRendererComponent(this, this.getValueAt(
                            row, col), false, false, row, col);
                } else {
                    return super.prepareRenderer(renderer, row, col);
                }
            }
        };
        table2.setAutoCreateRowSorter(false);
        table2.setPreferredScrollableViewportSize(new Dimension(600, 200));
        final JTableHeader header = table2.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(table2));
      //  table2.setToolTipText("Click this button to disable the middle button.");
        table2.setToolTipText("<html>Process time - time since set date and schedule date <br>Waiting time - Time spent in waiting room</html>");
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]
            		{
                {str[0][0], str[0][1], str[0][2], str[0][3]},
                {str[1][0], str[1][1], str[1][2], str[1][3]},
                {str[2][0], str[2][1], str[2][2], str[2][3]},
                {str[3][0], str[3][1], str[3][2], str[3][3]},
                {str[4][0], str[4][1], str[4][2], str[4][3]},
                {str[5][0], str[5][1], str[5][2], str[5][3]},
                {str[6][0], str[6][1], str[6][2], str[6][3]}, 
            },
            new String[]{
                "", "# of treated patients", "Process times", "Wait time since Ap date"
            }));
        scrollPane2 = new JScrollPane(table2);//.add(table2);// = new JScrollPane(table2);
        this.add(scrollPane2,BorderLayout.NORTH);
        pack();
    }
/*
    public static void main(String args[]) {
        //java.awt.EventQueue.invokeLater(new Runnable() {

            //@Override
            //public void run() {
                new MyTableExample3().setVisible(true);
               // new table2().setVisible(true);
            //}
//        });
    }
*/
    
    private String[][] getWeeklySummery(){
    	
    	String [][] return_data = new String[4][3];
    	
    	for(int col=0; col<3 ;col++){
    		
    			return_data[0][col] = String.valueOf(this.getMean(col+1)); //Average
    			return_data[1][col] = this.getMin(col+1);	//Minimum
    			return_data[2][col] = this.getMax(col+1);		//Max
    			return_data[3][col] = String.valueOf(this.getStdDev(col+1)); //sd
		
    	}
    	
    	return return_data;
    }
    
    //avg of a week
    private double getMean(int col)
     {
     	
         double sum = 0.0;
         for(int row=0; row<7 ; row++){
         	 if(!str[row][col].equals("empty") && str[row][col]!=null ){
         		 System.out.println("row = "+ row + "col = " + col);
         		 sum += Integer.parseInt(str[row][col]);
             }
         }
         return sum/7;
     }
    
   private String getMin(int col){
	    Integer smallest =0;
	   
	   for(int row=1; row< 7; row++){
		   if(!str[row][col].equals("empty") && str[row][col]!=null ){
			   if (Integer.parseInt(str[row][col]) < smallest) { smallest = Integer.parseInt(str[row][col]);}
		   }
       }
	   
	   return smallest.toString();
   }
   
   
   private String getMax(int col){
	    Integer largest=0;
	   
	   for(int row=1; row< 7; row++){
		   if(!str[row][col].equals("empty") && str[row][col]!=null ){
             if(Integer.parseInt(str[row][col]) > largest){ largest = Integer.parseInt(str[row][col]);}
		   }
      }
	   return largest.toString();
   }
   
   
 

    //variance of a week
    double getVariance(int col)
    {
        double mean = getMean(col);
        double temp = 0;
        for(int row=0; row<7 ; row++){
        	 if(!str[row][col].equals("empty") && str[row][col]!=null ){
                temp += (mean-Integer.parseInt(str[row][col]))*(mean-Integer.parseInt(str[row][col]));
            }
       
        }
        return temp/7;
     }

    
    // standard deviation of a week
    double getStdDev(int col)
    {
        return Math.sqrt(getVariance(col));
    }

    
    private static class HeaderRenderer implements TableCellRenderer {

        TableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }

        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, col);
        }
    }
    
    
   
    
}