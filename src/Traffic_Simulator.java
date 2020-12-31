import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Traffic_Simulator extends JFrame implements Runnable,ActionListener {
    private Vehicle Vehicle_Containers[]=new Vehicle[30];
    private Create CityContainer[]=new Create[5];

    private City_Content City =new City_Content();
    boolean running=false;
    boolean open =false;

    private JPanel contentPane;
    JLabel Value =new JLabel("");
    JButton RunBut = new JButton("Run");
    JButton StopBut = new JButton("Stop");
    JLabel SimStatus = new JLabel("Simulator Status: Idle");

    int total=0;
    int index=0;
    int i=0;
    int city=0;
    int count=0;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Traffic_Simulator Frame = new Traffic_Simulator();
                    Frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Traffic_Simulator() {
        setTitle("Traffic Simulator by Keashyn Naidoo");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1058, 629);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        addTraffic(-10,0);
        StopBut.setBounds(910, 220, 90, 25);
        StopBut.addActionListener(this);
        contentPane.add(StopBut);

        RunBut.setBounds(910, 155, 90, 25);
        RunBut.addActionListener(this);
        contentPane.add(RunBut);

        JLabel MenuTitle = new JLabel("Menu");
        MenuTitle.setFont(new Font("Arial", Font.BOLD, 30));
        MenuTitle.setBounds(910, 10, 100, 35);
        MenuTitle.setForeground(Color.WHITE);
        contentPane.add(MenuTitle);

        JButton CityBut = new JButton("City\r\n");
        CityBut.setBounds(865, 80, 85, 23);
        contentPane.add(CityBut);

        JButton SimulatorBut = new JButton("Sim");
        SimulatorBut.setBounds(960, 80, 75, 23);
        contentPane.add(SimulatorBut);

        JButton OpenCityBut = new JButton("Open City\r\n");
        OpenCityBut.setBounds(910, 270, 90, 25);
        OpenCityBut.setVisible(false);
        contentPane.add(OpenCityBut);

        JButton EditCityBut = new JButton("Edit City");
        EditCityBut.setBounds(910, 215, 90, 25);
        EditCityBut.setVisible(false);
        contentPane.add(EditCityBut);

        JButton CreateCityBut = new JButton("Create New City");
        CreateCityBut.setBounds(880, 155, 150, 25);
        CreateCityBut.setVisible(false);
        contentPane.add(CreateCityBut);

        JLabel ModeText = new JLabel("Mode: Simulation");
        ModeText.setForeground(Color.WHITE);
        ModeText.setBounds(880, 400, 120, 25);
        contentPane.add(ModeText);


        SimStatus.setForeground(Color.WHITE);
        SimStatus.setBounds(880, 430, 150, 25);
        contentPane.add(SimStatus);

        JLabel CityName = new JLabel("City Name: Default City");
        CityName.setForeground(Color.WHITE);
        CityName.setBounds(880, 465, 150, 25);
        contentPane.add(CityName);

        JLabel VehicleNum = new JLabel("Total Vehicles");
        VehicleNum.setForeground(Color.WHITE);
        VehicleNum.setBounds(880, 500, 150, 25);
        contentPane.add(VehicleNum);

        Value.setText(String.valueOf(total));
        Value.setForeground(Color.WHITE);
        Value.setBounds(968, 500, 70, 25);
        contentPane.add(Value);

        contentPane.add(City);
        SimulatorBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                RunBut.setVisible(true);
                StopBut.setVisible(true);

                OpenCityBut.setVisible(false);
                EditCityBut.setVisible(false);
                CreateCityBut.setVisible(false);
                SimStatus.setVisible(true);
                ModeText.setText("Mode: Simulation");
                SimStatus.setText("Simulator Status: Idle");
                VehicleNum.setText("Total Vehicles: ");
                CityName.setText("City Name: Default City");
                CityName.setVisible(true);
                VehicleNum.setVisible(true);
                Value.setVisible(true);
                City.setVisible(true);

            }
        });
        CityBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                RunBut.setVisible(false);
                StopBut.setVisible(false);

                OpenCityBut.setVisible(true);
                EditCityBut.setVisible(true);
                CreateCityBut.setVisible(true);
                SimStatus.setVisible(true);
                ModeText.setText("Mode: City Editing");
                SimStatus.setText("Simulator Status: Editing");
                VehicleNum.setText("Total Vehicle: ");
                CityName.setText("City Name: -");
                CityName.setVisible(true);
                VehicleNum.setVisible(true);
                CityName.setText("City Name: -");
                Value.setVisible(false);
            }
        });
        CreateCityBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(city<5) {
                    City.setVisible(true);
                    JOptionPane j=new JOptionPane();
                    String name=j.showInputDialog("Enter you city name");
                    int roads=Integer.parseInt(j.showInputDialog("Enter number of roads you want "));

                    RunBut.setVisible(false);
                    StopBut.setVisible(false);

                    ModeText.setText("Mode: City Editing");
                    SimStatus.setText("Simulator Status: Editing");
                    VehicleNum.setText("Total Vehicle: ");
                    CityName.setVisible(true);
                    VehicleNum.setVisible(true);
                    Value.setVisible(false);
                    Create create=new Create(name,roads);
                    CityContainer[city]=create;
                    j.showMessageDialog(contentPane, "You have succesfully created "+name+".");
                    CityName.setText("City Name : "+name);
                    City.setVisible(false);
                    contentPane.add(CityContainer[city]);
                    for(int i=0;i<city;i++) {
                        CityContainer[i].setVisible(false);
                    }
                    addTraffic(CityContainer[city].NumRoad,city);
                    CityContainer[city].setVisible(true);
                    city++;

                }
                else {
                    JOptionPane.showMessageDialog(contentPane, "You can't create cities more then 5");
                }


            }
        });
        EditCityBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpenCityBut.setVisible(true);
                EditCityBut.setVisible(true);
                CreateCityBut.setVisible(true);

                RunBut.setVisible(false);
                StopBut.setVisible(false);

                JOptionPane j=new JOptionPane();
                if(city!=0) {
                    String name=j.showInputDialog("Enter your city name");
                    int i=0;
                    while(i!=city) {
                        if(CityContainer[i].Cityname.equalsIgnoreCase(name)) {
                            int roads=Integer.parseInt(j.showInputDialog("Enter new number of Roads "));
                            CityContainer[i].NumRoad =roads;
                            JOptionPane.showMessageDialog(contentPane, CityContainer[i].Cityname +" Edited succesfully!");
                            for(int m=0;m<city;m++) {
                                CityContainer[m].setVisible(false);
                            }
                            CityContainer[i].Traffic_Container.clear();
                            addTraffic(roads,i);
                            CityContainer[i].setVisible(true);
                        }
                        i++;
                    }

                }
                else {
                    JOptionPane.showMessageDialog(contentPane, "City not Found!");

                }
            }

        });
        OpenCityBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(city>0) {
                    String name=JOptionPane.showInputDialog("Enter your city name");
                    for(int i=0;i<city;i++) {
                        if(CityContainer[i].Cityname.equalsIgnoreCase(name)) {
                            open=true;
                            index= CityContainer[i].NumRoad;
                            i=i;
                            CityContainer[i].setVisible(true);
                            RunBut.setVisible(true);
                            StopBut.setVisible(true);

                            OpenCityBut.setVisible(false);
                            EditCityBut.setVisible(false);
                            CreateCityBut.setVisible(false);
                            ModeText.setText("Mode: Simulation");
                            CityName.setText("City:"+ CityContainer[i].Cityname);
                            CityName.setVisible(true);
                            VehicleNum.setVisible(true);
                            Value.setVisible(true);
                            total=0;

                        }
                    }

                }
                else {
                    JOptionPane.showMessageDialog(contentPane, "No Cities Found!");
                }
            }
        });

    }
    public void check() {
        for(int j=0;j<total;j++) {
            if(Vehicle_Containers[j].check) {
                for(int k=j;k<total;k++) {
                    Vehicle_Containers[k]= Vehicle_Containers[k+1];
                }
                total--;
            }

        }
    }
    public void addTraffic(int index, int i) {
        if(index>0 & index<=2) {
        }
        else if(index>2 & index<=4) {
            CityContainer[i].addTraffic(new Traffics(310,117,true,false,true));
            CityContainer[i].addTraffic(new Traffics(372, 60,true,false,false));
            CityContainer[i].addTraffic(new Traffics(372, 150,true,false,false));
            CityContainer[i].addTraffic(new Traffics(395, 117,true,false,true));
        }
        else if(index>4 & index<=6) {
            CityContainer[i].addTraffic(new Traffics(310, 117,true,false,true));
            CityContainer[i].addTraffic(new Traffics(372, 60,true,false,false));
            CityContainer[i].addTraffic(new Traffics(372, 150,true,false,false));
            CityContainer[i].addTraffic(new Traffics(395, 117,true,false,true));
            CityContainer[i].addTraffic(new Traffics(372, 445,true,false,false));
        }
        else if(index>6 &  index<=8) {
            Traffics traffic = new Traffics(310, 117, true, false, true);
            CityContainer[i].addTraffic(traffic);
            traffic =new Traffics(372, 60,true,false,false);
            CityContainer[i].addTraffic(traffic);

            traffic =new Traffics(372, 150,true,false,false);
            CityContainer[i].addTraffic(traffic);
            traffic =new Traffics(395, 117,true,false,true);
            CityContainer[i].addTraffic(traffic);

            traffic =new Traffics(170, 445,true,false,false);
            CityContainer[i].addTraffic(traffic);
            traffic =new Traffics(372, 445,true,false,false);
            CityContainer[i].addTraffic(traffic);
            traffic =new Traffics(672, 445,true,false,false);
            CityContainer[i].addTraffic(traffic);
        }
        else {
            City.addTraffic(new Traffics(310, 117,true,false,true));
            City.addTraffic(new Traffics(372, 60,true,false,false));

            City.addTraffic(new Traffics(372, 150,true,false,false));
            City.addTraffic(new Traffics(395, 117,true,false,true));

            City.addTraffic(new Traffics(170, 445,true,false,false));
            City.addTraffic(new Traffics(372, 445,true,false,false));
            City.addTraffic(new Traffics(672, 445,true,false,false));}
    }
    public void addVehicle(int index,int i) {
        if(index>0 & index<=2) {
            Vehicle_Containers[total]=new Bus(804, 135);
            Vehicle_Containers[total].turn3();
            CityContainer[i].addVehicle(Vehicle_Containers[total]);
            total++;
        }
        else if(index>2 & index<=4) {
            int a=(int) (Math.random() * 3);
            if(a==0) {
                Vehicle_Containers[total]=new Bus(804, 135);
                Vehicle_Containers[total].turn3();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==1) {
                Vehicle_Containers[total]=new Car(354, 0);
                Vehicle_Containers[total].turn1();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}
        }
        else if(index>4 & index<=6) {
            int a=(int) (Math.random() * 5);
            if(a==0) {
                Vehicle_Containers[total]=new Bus(804, 135);
                Vehicle_Containers[total].turn3();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==1) {
                Vehicle_Containers[total]=new Car(354, 0);
                Vehicle_Containers[total].turn1();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==2) {
                Vehicle_Containers[total]=new Bike(385, 580);
                Vehicle_Containers[total].turn2();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}
            else if(a==3) {

                Vehicle_Containers[total]=new Car(0, 404);

                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}
        }
        else if(index>6 &  index<=8) {
            int a=(int) (Math.random() * 5);
            if(a==0) {
                Vehicle_Containers[total]=new Bus(804, 135);
                Vehicle_Containers[total].turn3();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==1) {
                Vehicle_Containers[total]=new Car(354, 0);
                Vehicle_Containers[total].turn1();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==2) {
                Vehicle_Containers[total]=new Bike(385, 580);
                Vehicle_Containers[total].turn2();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==3) {
                Vehicle_Containers[total]=new Car(0, 404);
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==4) {
                Vehicle_Containers[total]=new Bike(185, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==5) {
                Vehicle_Containers[total]=new Bike(685, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;}
        }


        else {
            int a=(int) (Math.random() * 5);
            if(a==0) {
                Vehicle_Containers[total]=new Bike(185, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==1) {
                Vehicle_Containers[total]=new Car(385, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==2) {
                Vehicle_Containers[total]=new Bike(685, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==3) {
                Vehicle_Containers[total]=new Car(354, 0);
                Vehicle_Containers[total].turn1();
                City.addVehicle(Vehicle_Containers[total]);
                total++;}

            else if(a==4) {
                Vehicle_Containers[total]=new Bus(810, 135);
                Vehicle_Containers[total].turn3=true;
                City.addVehicle(Vehicle_Containers[total]);
                total++;}
            else {
                Vehicle_Containers[total]=new Bus(0, 404);
                City.addVehicle(Vehicle_Containers[total]);
                total++;}

        }
    }
    public void run() {
        while(running & !open) {
            City.Move();
            City.repaint();
            check();
            Value.setText(String.valueOf(total));
            if(count==10 & total!=29) {
                addVehicle(-10,0);
                count=0;
            }
            count++;

            try {
                Thread.sleep(500);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        while(running & open) {
            CityContainer[i].Move();
            CityContainer[i].repaint();
            check();
            Value.setText(String.valueOf(total));
            if(count==10 & total!=29) {
                addVehicle(index,i);
                count=0;
            }
            count++;

            try {
                Thread.sleep(500);
            }
            catch(Exception e) {
                e.printStackTrace();
            }}
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(RunBut)) {
            if(!running) {
                running=true;
                SimStatus.setText("Simulator Status: Run");
                Thread t=new Thread(this);
                t.start();
            }
        }
        if(event.getSource().equals(StopBut)) {
            SimStatus.setText("Simulator Status: Stop");
            running=false;
        }
    }
}