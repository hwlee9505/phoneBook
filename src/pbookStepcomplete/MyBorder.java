package pbookStepcomplete;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class MyBorder implements KeyListener {

    JFrame mainFrame = new JFrame();                    //프레임
    JPanel mainPane = new JPanel();                     //패널
    JTextArea menuDisplay = new JTextArea();            //컴포넌트
    JTextArea printDisplay = new JTextArea();           //컴포넌트
    JTextField inputDisplay = new JTextField();         //컴포넌트
    JScrollPane printScrollPane;

    //프레임에 패널이 있다.
    //1) TextArea(menuDisplay)
    //2) TextArea(printDisplay) - > JScrollPane(printScrollPane)에 쌓여있음
    //3) TextField(inputDisplay)

    //MODE 0 메뉴 31 입력메뉴 중 이름입력 32 입력메뉴 중 전화번호 입력 33 입력메뉴 이름만 중복 시 41 삭제메뉴 중 이름입력
    int MODE = 0;

    String name, phone = "";
    int num = 0;

    ArrayList<PhoneItem> phonelist = new ArrayList<>();
    ArrayList<Integer> countNum = new ArrayList<>();

    PhoneItem delitem = new PhoneItem();

    public MyBorder() throws IOException {
        //화면그려주기
        makeDisplay();
        ///1 phonebook.txt 파일을 읽어서  ArrayList의 phoneItem클래스타입으로 phonelist 변수에 담아라
        makephonelist();
    }

    //frame > panel > component

    public void makeDisplay() {

        mainFrame.setTitle("phoneBook");
        mainFrame.setSize(600, 400);            //처음 창의 크기
        mainFrame.setDefaultCloseOperation(3);
        mainFrame.setLocationRelativeTo(null);

        menuDisplay.setBorder(new TitledBorder("menu"));
        menuDisplay.setSize(240, 230);
        menuDisplay.setLocation(10, 20);
        menuDisplay.setEditable(false);                         // 타이핑 못하게 막음

        printDisplay.setSize(240, 230);
        printDisplay.setLocation(255, 20);
        printDisplay.setEditable(false);
        printDisplay.setFocusable(false);
        printDisplay.setRequestFocusEnabled(false);
        printDisplay.setWrapStyleWord(true);
        printDisplay.setLineWrap(true);

        printScrollPane = new JScrollPane(printDisplay);            //스크롤 패널에
        printScrollPane.setBorder(new TitledBorder("display"));
        printScrollPane.setSize(240, 230);
        printScrollPane.setLocation(255, 20);

        inputDisplay.setBorder(new TitledBorder("input"));
        inputDisplay.setSize(400, 40);
        inputDisplay.setLocation(10, 250);
        inputDisplay.addKeyListener(this);

        mainPane.setBorder(new TitledBorder("main"));
        mainPane.setLayout(null);
        mainPane.setLocation(0, 0);

        mainPane.add(menuDisplay);
        mainPane.add(printScrollPane);
        mainPane.add(inputDisplay);

        mainFrame.getContentPane().add(mainPane, null);
        //	mainFrame.getContentPane().add(printScrollPane,null);
        //	mainFrame.getContentPane().add(inputPane,null);

        //mainFrame.pack();
        mainFrame.setVisible(true);

        inputDisplay.requestFocus();

        menuDisplay.setText(Properties.menu_EX1);
        // 빨간줄


    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        int keyN = arg0.getKeyCode();

        System.out.println(keyN);

        //menu1
        if (keyN == 49 && MODE == 0) {
            menu1();
            //menu2
        } else if (keyN == 50 && MODE == 0) {
            menu2_name();
            MODE = 31;
        } else if (keyN == 10 && MODE == 31) {
            menu2_phone();
            MODE = 32;
        } else if (keyN == 10 && MODE == 32) {
            menu2();
        } else if (keyN == 89 && MODE == 33) {
            //이름은 같지만 전화번호가 달랐고,유저가 중복을 허용해서 저장
            menu2_force();
            MODE = 0;
        } else if (keyN == 78 && MODE == 33) {
            menu2_no();
            MODE = 0;
        } else if (keyN == 51 && MODE == 0) {
            menu3();
            MODE = 34;
        } else if (keyN == 10 && MODE == 34) {
            menu3_sub();
        } else if (keyN == 52 && MODE == 0) {
            menu4();
            MODE = 35;
        } else if (keyN == 10 && MODE == 35) {
            menu4_sub();
        } else if (keyN == 10 && MODE == 36) {
            menu4_sub2();
            MODE = 37;

        } else if (keyN == 10 && MODE == 37) {
            menu4_sub3();
            MODE = 0;
        }

        if (MODE == 0 || keyN == 10 || (MODE == 33 && keyN == 89) || (MODE == 33 && keyN == 78))
            inputDisplay.setText("");
    }


    public void makephonelist() throws IOException {

        try {
            ///1-1 phonebook.txt파일 읽어오기
            FileReader fr = new FileReader(Properties.file_PATH);
            BufferedReader br = new BufferedReader(fr);
            //            DataInputStream fis = new DataInputStream(fi);

            ///1-2 phonelist 변수에 파일의 내용 담기
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] tempArr = line.split(",");
//				PhoneItem p = new PhoneItem();
//				p.setName(tempArr[0]);
//				p.setTeleNum(tempArr[1]);
//				phonelist.add(p);
                phonelist.add(new PhoneItem(tempArr[0], tempArr[1]));
            }
            br.close();


        } catch (FileNotFoundException e) {
            System.err.println("<!FileOpen 오류>" + e.getMessage());

        }


    }


    //2 키보드 1을 눌렀을 때 발생하는 이벤트로 printDisplay 창에 phonelist의 값을 보여준다

    public void menu1() {
//        printDisplay.setText("testtest");
        //setText == 사라짐
        //append == 추가적으로 계속 출력됨
//        for(int i =0; i< phonelist.size(); i++){
        printDisplay.setText("");
//        for (PhoneItem delitem : phonelist) {
////            printDisplay.append(item.getName() + "," + item.getTeleNum() + "\n");
//            printDisplay.append(delitem.toString());
//        }

        phonelist.forEach(delitem -> printDisplay.append(delitem.toString()));

        printDisplay.append("\n[출력이 완료되었습니다.]\n[메뉴버튼을 입력하세요.] \n");
        ///
        inputDisplay.setText("");
        ///
    }
    //3-1 모드를 이름입력 단계로 변경하고 , printDisplay 화면에 관련 설명을 뿌려준다

    public void menu2_name() {
        inputDisplay.setText("");
        printDisplay.setText(Properties.menu2_name);
    }
    //3-2 사용자가 입력하고 엔터를 누른 순간의 이름 값을 변수에 담고
    //모드를 전화번호 입력 단계로 변경 후
    //printDisplay 화면에 관련 설명을 뿌려준다

    public void menu2_phone() {

        name = inputDisplay.getText();
        inputDisplay.setText("");
        printDisplay.setText(Properties.menu2_phone);


    }
    //3-3  사용자가 입력하고 엔터를 누른 순간의 전화번호 값을 변수에 담고
    //모드를 메뉴 입력 단계로 변경 후

    //입력받은 이름이 phonelist에 있는지 중복 체크를하고
    // 만약 전화번호까지 같다면 중복되어 입력할 수 없다고 하고 끝내고


    // 전화번호만 다르면 이름만 같은 모드로 바꾼 후 사용자에게 printDisplay 이름이 중복인데 저장할 것인지 물어본후
    //  저장한다고 하면 menu2_force()에서 저장하고 메뉴입력모드로 돌아가고
    //  저장안한다고 menu2_no()에서하면 printDisplay에 입력을 취소하였다고 하고 메뉴입력으로 돌아간다

    //만약 입력받은 이름이 달랐다면 phonelist에 저장하고, printDisplay에 저장되었다고 출력후 메뉴입력단계로 간다

    public void menu2() {

        int chk = 0;

        phone = inputDisplay.getText();
        inputDisplay.setText("");

        System.out.println("name = " + name);
        System.out.println("phone = " + phone);

        if (isDuplicatedPhoneListByName(name)) {

            System.out.println("이름이 중복임");
            if (isDuplicatedPhoneListByPhone(phone)) {                  // 이름 O 전화번호 O
                printDisplay.setText(Properties.menu2_duplicate);
            } else {                                                    // 이름 O 전화번호 X
                chk = 33;
                printDisplay.setText(Properties.menu2_duplicate2);
            }

        } else {

            if (isDuplicatedPhoneListByPhone(phone)) {                  // 이름 X 전화번호 O
                printDisplay.setText(Properties.menu2_duplicate3);

            } else {
                phonelist.add(new PhoneItem(name, phone));              // 이름 X 전화번호 X
                printDisplay.setText(Properties.menu2_add);
                fileSave();
            }
        }
        MODE = chk;

    }
    //이름은 같지만 전화번호가 달랐고,유저가 중복을 허용해서 저장

    public void menu2_force() {
        phonelist.add(new PhoneItem(name, phone));
        printDisplay.setText(Properties.menu2_add);
        fileSave();
    }

    public void menu2_no() {
        printDisplay.setText(Properties.menu2_no);
    }

    public void menu3() {
        inputDisplay.setText("");
        printDisplay.setText(Properties.menu3_name);
    }

    public void menu3_sub() {
        name = inputDisplay.getText();
        if (isDuplicatedPhoneListByName(name)) {
            printDisplay.setText(Properties.menu3_del);

            while (true) {

                if (!isDuplicatedPhoneListByName(name)) {
                    break;
                }

                for (int i = phonelist.size() - 1; i >= 0; i--) {
                    if (name.equals(phonelist.get(i).getName())) {
                        System.out.println(i + "번째 삭제 [" + phonelist.get(i).getName() + "/" + phonelist.get(i).getTeleNum() + "]");
                        phonelist.remove(i);
                        fileSave();
                        continue;
                    }
                }
            }

        } else {
            printDisplay.setText(Properties.menu3_delno);
        }
        MODE = 0;
    }

    public void menu4() {
        inputDisplay.setText("");
        printDisplay.setText(Properties.menu4_name);
    }

    public void menu4_sub() {

        int chk = 0;

        name = inputDisplay.getText();
        if (isDuplicatedPhoneListByName(name)) {
            printDisplay.setText(Properties.menu4_phone2);
            chk = 36;

            for (int i = 0; i < phonelist.size(); i++) {
                if (name.equals(phonelist.get(i).getName())) {
                    countNum.add(i);
                }
            }
            printDisplay.append("[수정하려 하는 이름 : " + name + "]\n");

            countNum.forEach(i -> printDisplay.append("[" + (i) + "] " + phonelist.get(countNum.get(i-1)).getTeleNum() + "\n"));
//            for (int i = 0; i < countNum.size(); i++) {
//                printDisplay.append("[" + (i + 1) + "] " + phonelist.get(countNum.get(i)).getTeleNum() + "\n");
//            }

        } else {
            printDisplay.setText(Properties.menu4_reviseno);
        }
        MODE = chk;
    }

    public void menu4_sub2() {

        num = Integer.parseInt(inputDisplay.getText()) - 1;
        printDisplay.setText(Properties.menu4_phone);
        printDisplay.append(phonelist.get(countNum.get(num)).getTeleNum() + " -> ");

    }

    public void menu4_sub3() {
        phone = inputDisplay.getText();
        phonelist.get(countNum.get(num)).setTeleNum(phone);
        printDisplay.setText(Properties.menu4_revise);
        fileSave();
        countNum.clear();
    }

    //3-3 phonelist에 내용이 추가될 때마다 phonebook.txt 파일에다가 내용을 저장한다

    public void fileSave() {
        try {
            FileWriter fw = new FileWriter(
                    Properties.file_PATH);

            for (int i = 0; i < phonelist.size(); i++) {
                PhoneItem pi = phonelist.get(i);
                fw.write(pi.getName() + "," + pi.getTeleNum() + "\r\n");
            }
            fw.close();
            System.out.println("저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDuplicatedPhoneListByName(String name) {

        for (int i = 0; i < phonelist.size(); i++) {
            if ((phonelist.get(i).getName()).equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDuplicatedPhoneListByPhone(String phone) {

        for (int i = 0; i < phonelist.size(); i++) {
            if ((phonelist.get(i).getTeleNum()).equals(phone)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        new MyBorder();
    }


}
