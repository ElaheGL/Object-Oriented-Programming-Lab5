import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Form extends JFrame implements ActionListener {
    private JLabel carInfo,labelBrand,labelGearBox,labelModel,labelYear,labelmaxSpeed,labelColor;
    private JTextField jTextFieldModel,jTextFieldYear,jTextFieldmaxSpeed,
    jTextFieldColor;
    private JButton buttonAddCar,buttonOpenForm;
    private JRadioButton manual,automatic;
    private JComboBox chooseBrand;

    public Form(){
        setSize(500,400);
        setTitle("CarInfo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel
        Panel formPanel = new Panel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets= new Insets(5,5,5,5);
        gbc.anchor=GridBagConstraints.WEST;

        //Headline:
        carInfo = new JLabel("Enter The Car's Information");
        carInfo.setFont(new Font("Arial",Font.BOLD,16));
        carInfo.setForeground(Color.BLUE);
        Panel headline = new Panel();
        headline.add(carInfo);


        //choose the brand part:
        labelBrand = new JLabel("Choose the brand:");
        formPanel.add(labelBrand,gbc);
        gbc.gridx++;
        chooseBrand = new JComboBox(new String[] {"choose","Mercedes","BMW","HONDA","Mazda","Suzuki","Hyundai"});
        formPanel.add(chooseBrand,gbc);
        gbc.gridx=0;
        gbc.gridy++;

        //choose the gearbox:
        labelGearBox= new JLabel("Choose the Gearbox:");
        formPanel.add(labelGearBox,gbc);
        gbc.gridx++;
        manual = new JRadioButton("Manual");
        automatic = new JRadioButton("Automatic");
        ButtonGroup GearBox = new ButtonGroup();
        GearBox.add(automatic);
        GearBox.add(manual);
        manual.addActionListener(this);
        automatic.addActionListener(this);
        Panel gearboxPanel = new Panel();
        gearboxPanel.add(manual);
        gearboxPanel.add(automatic);
        formPanel.add(gearboxPanel,gbc);
        gbc.gridx=0;
        gbc.gridy++;

        //Enter model:
        labelModel = new JLabel("Enter the model :");
        formPanel.add(labelModel,gbc);
        gbc.gridx++;
        jTextFieldModel = new JTextField(20);
        formPanel.add(jTextFieldModel,gbc);
        gbc.gridx=0;
        gbc.gridy++;

        //Enter the year:
        labelYear = new JLabel("Enter the year:");
        formPanel.add(labelYear,gbc);
        gbc.gridx++;
        jTextFieldYear = new JTextField(20);
        formPanel.add(jTextFieldYear,gbc);
        gbc.gridx=0;
        gbc.gridy++;

        // max speed part:
        labelmaxSpeed = new JLabel("Enter the max speed:");
        formPanel.add(labelmaxSpeed,gbc);
        gbc.gridx++;
        jTextFieldmaxSpeed = new JTextField(20);
        formPanel.add(jTextFieldmaxSpeed,gbc);
        gbc.gridx=0;
        gbc.gridy++;

        //color part:
        labelColor = new JLabel("Enter the color :");
        formPanel.add(labelColor,gbc);
        gbc.gridx++;
        jTextFieldColor = new JTextField(20);
        formPanel.add(jTextFieldColor,gbc);
        gbc.gridx=0;
        gbc.gridy++;

        //button part:
        buttonAddCar = new JButton("Add to File");
        buttonOpenForm = new JButton("Clear");
        buttonOpenForm.addActionListener(this);
        buttonAddCar.addActionListener(this);
        Panel buttonPanel = new Panel();
        buttonPanel.add(buttonAddCar);
        buttonPanel.add(buttonOpenForm);


        this.add(buttonPanel,BorderLayout.SOUTH);
        this.add(headline,BorderLayout.NORTH);



        this.add(formPanel,BorderLayout.CENTER);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAddCar) addCarToFile();
        else if(e.getSource()== buttonOpenForm) clearForm();

    }

    private void addCarToFile(){
        String brand = (String) chooseBrand.getSelectedItem();
        String gearBox= manual.isSelected() ? "Manual" : "Automatic";
        String model = jTextFieldModel.getText();
        String year = jTextFieldYear.getText();
        String maxSpeed = jTextFieldmaxSpeed.getText();
        String color = jTextFieldColor.getText();

        String carInfoString = "Brand: " + brand + "\n" +
                "Gearbox: " + gearBox + "\n" +
                "Model: " + model + "\n" +
                "Year: " + year + "\n" +
                "Max Speed: " + maxSpeed + "\n" +
                "Color: " + color;
        saveCarInfoToFile(carInfoString);

    }
    private void saveCarInfoToFile( String carInfoString){
        String filePath = "D:\\car_info.txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))){
            writer.write(carInfoString);
            writer.newLine();

        } catch (IOException ex) {

            ex.printStackTrace();

            System.out.println("Error");


        }


    }
    private void clearForm(){
        jTextFieldmaxSpeed.setText(" ");
        jTextFieldColor.setText(" ");
        jTextFieldYear.setText(" ");
        jTextFieldModel.setText(" ");
        chooseBrand.setSelectedIndex(0);

    }
}
