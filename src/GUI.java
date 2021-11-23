import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    public void main_gui() {
        // Main GUI 크기 및 타이틀 설정
        setSize(400, 400);
        setTitle("백신");

        // container 설정
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.pink);

        // 그룹을 새로 생성하는 버튼 관련 GUI 처리 코드
        JButton BTN_group_add = new JButton();
        BTN_group_add.setLocation(25, 20);
        BTN_group_add.setSize(325, 30);
        BTN_group_add.setText("그룹 추가");
        BTN_group_add.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_group_add.setBackground(Color.gray);
        BTN_group_add.setOpaque(true);

        contentPane.add(BTN_group_add);

        // 그룹 추가 버튼을 클릭했을 때 해당 GUI로 이동함.
        BTN_group_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                group_add();
            }
        });

        // 그룹 정보를 조회하는 버튼 관련 GUI 처리 코드
        JButton BTN_group_show = new JButton();
        BTN_group_show.setLocation(25, 70);
        BTN_group_show.setSize(150, 120);
        BTN_group_show.setText("그룹 정보 조회");
        BTN_group_show.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_group_show.setBackground(Color.gray);
        BTN_group_show.setOpaque(true);

        contentPane.add(BTN_group_show);

        // 그룹 정보를 수정하는 버튼 관련 GUI 처리 코드
        JButton BTN_group_modify = new JButton();
        BTN_group_modify.setLocation(200, 70);
        BTN_group_modify.setSize(150, 120);
        BTN_group_modify.setText("그룹 정보 수정");
        BTN_group_modify.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_group_modify.setBackground(Color.gray);
        BTN_group_modify.setOpaque(true);

        contentPane.add(BTN_group_modify);

        // 그룹을 완전 삭제하는 버튼 관련 GUI 처리 코드
        JButton BTN_group_delete = new JButton();
        BTN_group_delete.setLocation(25, 220);
        BTN_group_delete.setSize(150, 120);
        BTN_group_delete.setText("그룹 삭제");
        BTN_group_delete.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_group_delete.setBackground(Color.lightGray);
        BTN_group_delete.setOpaque(true);

        contentPane.add(BTN_group_delete);

        // 최종 보고서를 출력하는 버튼 관련 GUI 처리 코드
        JButton BTN_report_print = new JButton();
        BTN_report_print.setLocation(200, 220);
        BTN_report_print.setSize(150, 120);
        BTN_report_print.setText("최종 보고서 출력");
        BTN_report_print.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_report_print.setBackground(Color.lightGray);
        BTN_report_print.setOpaque(true);

        contentPane.add(BTN_report_print);


        setVisible(true);
    }

    // 그룹 추가를 담당하는 함수
    public void group_add() {
        JFrame addFrame = new JFrame();
        addFrame.setSize(400, 200);
        addFrame.setTitle("그룹 추가 진행 중");

        Container addContainer = addFrame.getContentPane();
        addContainer.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(Color.lightGray);
        addContainer.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.lightGray);
        addContainer.add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setBackground(Color.lightGray);
        addContainer.add(south, BorderLayout.SOUTH);

        // 그룹 이름을 입력받습니다.
        JTextField groupname = new JTextField("그룹 이름",6);
        groupname.setSize(10,20);
        north.add(groupname, BorderLayout.WEST);
        clear_txt(groupname);

        // 이용 날짜를 입력받습니다.
         JTextField starttime = new JTextField("시작 시간",6);
        starttime.setSize(10,20);
        north.add(starttime, BorderLayout.WEST);
        clear_txt(starttime);

        JTextField endtime = new JTextField("종료 시간",6);
        endtime.setSize(10,20);
        north.add(endtime, BorderLayout.EAST);
        clear_txt(endtime);


        // 그룹원 이름을 입력받습니다.
        JTextField person_name = new JTextField("그룹원 이름", 7);
        center.add(person_name);
        clear_txt(person_name);

        // 생년월일을 입력받습니다.
        JTextField birthday = new JTextField("생년 월일 6자리를 입력하세요.", 15);
        center.add(birthday);
        clear_txt(birthday);

        // 주소를 입력받습니다.
        JTextField address = new JTextField("거주중인 주소를 입력하세요.", 30);
        center.add(address);
        clear_txt(address);

        // 백신 접종 여부를 확인합니다.
        JLabel vaccine = new JLabel("백신 접종 여부");
        vaccine.setSize(20, 10);
        JButton vaccine_yes = new JButton("O");
        vaccine_yes.setSize(10, 10);
        JButton vaccine_no = new JButton("X");
        vaccine_no.setSize(10, 10);

        center.add(vaccine);
        center.add(vaccine_yes);
        center.add(vaccine_no);

        // 음성 확인서 여부를 확인합니다.
        JLabel negative = new JLabel("음성 확인서 여부");
        negative.setSize(20, 10);
        JButton negative_yes = new JButton("O");
        negative_yes.setSize(10, 10);
        JButton negative_no = new JButton("X");
        negative_no.setSize(10, 10);

        negative.setVisible(false);
        negative_yes.setVisible(false);
        negative_no.setVisible(false);

        center.add(negative);
        center.add(negative_yes);
        center.add(negative_no);

        // 백신 미접종인 경우 음성 확인서 여부 패널을 나타나게 합니다.
        vaccine_no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                negative.setVisible(true);
                negative_yes.setVisible(true);
                negative_no.setVisible(true);
            }
        });

        // 그룹원 저장하기
        JButton BTN_addperson = new JButton();
        BTN_addperson.setText("그룹원 추가하기");
        BTN_addperson.setSize(30,30);
        south.add(BTN_addperson, BorderLayout.WEST);



        // 입력이 완료되면 저장하고 문자열을 초기화해야 합니다.
        // <<<<< 이 부분 문자 저장되는 거 코드 추가해주세요! >>>>>
        BTN_addperson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
//                arraylist로 저장해 return 해주시면 됩니다.
//                if(값 중에서 공백이 하나라도 있을 경우에 해당하는 조건) {
//                BTN_addperson.setText("정보 모두 입력!!");
//                JFrame warning = new JFrame();
//                warning.setSize(400, 0);
//                warning.setTitle("정보를 모두 입력하세요!");
//                warning.setVisible(true);
//                BTN_addperson.setText("그룹원 추가하기");
//                } else {
                groupname.setText("그룹 이름");
                starttime.setText("시작 시간");
                endtime.setText("종료 시간");
                person_name.setText("그룹원 이름");
                birthday.setText("생년 월일 6자리를 입력하세요.");
                address.setText("거주중인 주소를 입력하세요.");
                // }
            }
        });

        // 총 입력 완료하였을 경우
        JButton BTN_endAddgroup = new JButton();
        BTN_endAddgroup.setText("입력 완료");
        BTN_endAddgroup.setSize(30,30);
        south.add(BTN_endAddgroup, BorderLayout.EAST);

        // 입력 완료 버튼이 클릭된 경우
        BTN_endAddgroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.dispose();
            }
        });


        addContainer.setVisible(true);
        addFrame.setVisible(true);
    }

    // TextField를 클릭했을 때 설명 문구 초기화 시키는 함수입니다.
    public void clear_txt(JTextField txt) {
        txt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txt.setText("");
            }
        });
    }

    // 그룹 수정을 담당하는 함수
    public void group_modify() {

    }

    // 그룹 수정을 담당하는 함수
    public void group_delete() {

    }

    public void report_print() {

    }

}
