package Dominated_Point;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class domicated_point {
	 public static void main(String[] args)
	 {
	  
	  new MyFrame("Dominated Points Finder");
	 }
	}

	class MyFrame extends JFrame implements ActionListener
	{
	 ArrayList<Point> points = new ArrayList<Point>();
	 ArrayList<Integer> dom = new ArrayList<Integer>();
	 JPanel panel1 = new JPanel();
	 MyPanel panel2 = new MyPanel();
	 Button bl = new Button(" Start ");
	 Button br = new Button(" Clear ");
	 Button bran = new Button(" Random ");
	 Button bf = new Button(" File ");
	 Button bm = new Button(" Mouse ");
	 Button bd = new Button(" Delay ");
	 public static boolean b = false;
	 int delaytime = 1000;
	 MyFrame(String s)
	 {
	  super(s);
	  //this.setLayout(null);
	  //this.setBounds(100,100,200,300); 
	  bl.addActionListener(this);
	  br.addActionListener(this);
	  bran.addActionListener(this);
	  bf.addActionListener(this);
	  bm.addActionListener(this);
	  bd.addActionListener(this);
	  panel1.add(bl);
	  panel1.add(br);
	  panel1.add(bran);
	  panel1.add(bf);
	  panel1.add(bm);
	  panel1.add(bd);
	  panel1.setLayout(new FlowLayout());
	  panel1.setSize(500,60);
	  panel2.setSize(500,440);
	  panel2.setBackground(Color.white );
	  panel2.setLayout(new FlowLayout());
	  this.setLayout(new BorderLayout());
	  this.setSize(500,500);
	  this.setBackground(Color.white );
	  this.add(panel1);
	  this.add(panel2);
	  this.setVisible(true);
	  panel2.addMouseListener(new MyMouseListener());
	  //this.addWindowListener(new MyWindowMoniter());
	  this.addWindowListener(new WindowAdapter(){
	   public void windowClosing(WindowEvent e)
	   {
	    setVisible(false);
	    System.exit(0);
	   }
	  });
	 }
	 class MyPanel extends JPanel {
		 public void paint(Graphics g)
		 {
			 super.paintComponent(g);
			 Color c = g.getColor();
			 int j=0;
			 for (int i=0;i<points.size();i++)
			 {
				 Point p = points.get(i);
				 if (dom.size()>j) {
					 if (dom.get(j)==i) {
						 g.setColor(Color.red);
						 j++;
					 } else {
						 g.setColor(Color.gray);
					 }
				 } else {
					 g.setColor(Color.blue);
				 }
				 g.fillOval(p.x,p.y,10,10);
			 }
			 g.setColor(c);
		 }
		 public void addPoint(Point p)
		 {
		  points.add(p);
		 }
	 }
	 public void actionPerformed(ActionEvent e) {
		 	b = false;
	        if(e.getSource() == bl) {
	        	Point[] p = new Point[points.size()];
	        	points.toArray(p);
	        	dominated(p);
	        } else if(e.getSource() == br) {
	        	points.clear();
	        	dom.clear();
	        } else if(e.getSource() == bran) {
	        	// random generated points
	    		Random rand = new Random();
	    		String s = (String)JOptionPane.showInputDialog(
	                    this,
	                    "Please input number of points",
	                    "Number of points",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    null,
	                    null);
	    		int n = Integer.parseInt(s);
	    		Point[] p = new Point[n];

	    		for(int i = 0; i < n; i++)
	    		{
	    			int x = rand.nextInt(panel2.getSize().width-20)+10;
	    			int y = rand.nextInt(panel2.getSize().height-20)+10;
	    			p[i] = new Point(x, y);
	    			System.out.println(String.valueOf(p[i].x) + " " + String.valueOf(p[i].y));
	    		}
	    		points = new ArrayList<Point>(Arrays.asList(p));
	        } else if(e.getSource() == bf) {
	        	String filepath = "/Users/bihuayu/Documents/input.txt";
	  		  String temp = null;
	  		  //Object a[][] = null;
	  		  BufferedReader br = null;
	  		  StringTokenizer st = null;
	  		  int i = 0;
	  		  int n = 0;
	  		  Point[] p = null;
	  		  int Xmax = Integer.MIN_VALUE;
	  		  int Xmin = Integer.MAX_VALUE;
	  		  int Ymax = Integer.MIN_VALUE;
	  		  int Ymin = Integer.MAX_VALUE;
	  		  try {
	  		   br = new BufferedReader(new FileReader(filepath));
	  		   n = Integer.parseInt(br.readLine()); 
	  		   p = new Point[n];
	  		   while (br.ready()) {
	  		    temp = br.readLine();
	  		    if (temp != null && temp != "") {
	  		     st = new StringTokenizer(temp);
	  		     int x = Integer.parseInt(st.nextToken()); 
	  		     int y = Integer.parseInt(st.nextToken());
	  		     p[i] = new Point(x, y); 
	  		    }
	  		  i++;
	  		   }
	  		  } catch (Exception ex) {
	  		   ex.printStackTrace();
	  		  } finally {
	  		   try {
	  		    br.close();
	  		   } catch (Exception ex) {
	  		    ex.printStackTrace();
	  		   }
	  		  }
	    	  points = new ArrayList<Point>(Arrays.asList(p));
	        } else if(e.getSource() == bm) {
	        	b = true;
	        	System.out.println("Mouse");
	        } else if(e.getSource() == bd) {
	    		String s = (String)JOptionPane.showInputDialog(
	                    this,
	                    "Please input number of points",
	                    "Number of points",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    null,
	                    String.valueOf(delaytime));
	    		delaytime = Integer.parseInt(s);
	        	
	        }
	        panel2.repaint();
     }  
		public void dominated(Point[] p) { 
			// TODO Auto-generated method stub
			dom.clear();
        	String filepath = "/Users/benjaminlin/Documents/output.txt";
			int n = p.length;
			if (n==0) return;
			Arrays.sort(p, new Comparator<Point>() {
				public int compare(Point o1, Point o2) {
					double k= (o1.x-o2.x);
					if (k==0)
						return (int)(o1.y-o2.y);
					else return (int)k;
				}
			});
			points = new ArrayList<Point>(Arrays.asList(p)); 
			int maxx=0;
			for (int j = 1; j < n; j++)
			{
				if (p[maxx].y<p[j].y) {
					maxx = j;
				}
			}
			dom.add(new Integer(maxx));
			panel2.paintImmediately(panel2.getBounds());
			System.out.println("nondominated point(s) is(are):");
			System.out.println(String.valueOf(p[maxx].x) + " " + String.valueOf(p[maxx].y));
			try {
			    Thread.sleep(delaytime);
			} catch(InterruptedException ex) {
			    //Thread.currentThread().interrupt();
			}
			while (maxx<n-1) {
				int maxx1=maxx+1;
				for (int j = maxx+1;j<n;j++) {
					if (p[maxx1].y<=p[j].y&&p[maxx].x<p[j].x) {
						maxx1 = j;
					}				
				}
				maxx = maxx1;
				dom.add(new Integer(maxx));
				panel2.paintImmediately(panel2.getBounds());
				System.out.println(String.valueOf(p[maxx].x) + " " + String.valueOf(p[maxx].y));
				try {
				    Thread.sleep(delaytime);
				} catch(InterruptedException ex) {
				    //Thread.currentThread().interrupt();
				}
			}
	  		BufferedWriter br = null;
			try {
  				br = new BufferedWriter(new FileWriter(filepath));
  				Iterator<Integer> i = dom.iterator();
  				while (i.hasNext()) {
  					int p1 = (Integer)i.next();
  					br.write(String.valueOf(p[p1].x) + " " + String.valueOf(p[p1].y) + "\n");
  				}
			} catch (Exception ex) {
		  		   ex.printStackTrace();
			} finally {
			   try {
			    br.close();
			   } catch (Exception ex) {
			    ex.printStackTrace();
			   }
			}
			//return new ArrayList<Point>(Arrays.asList(p));
		}
	 
	}
	class MyMouseListener extends MouseAdapter
	{
	 public void mousePressed(MouseEvent e)
	 {
		 if (MyFrame.b) {
		  MyFrame.MyPanel mf = (MyFrame.MyPanel)e.getSource();
		  mf.addPoint((new Point(e.getX(),e.getY())));
		  mf.repaint();
		 }
	 }
	}


