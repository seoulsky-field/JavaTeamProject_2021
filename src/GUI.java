import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


public class GUI extends JFrame {
    public void main_gui() {
        setSize(300, 200);
        setTitle("초기 화면 수정");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBackground(Color.lightGray);

        JPanel north = new JPanel();
        north.setBackground(Color.lightGray);
        mainContainer.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.lightGray);
        center.setLayout(new GridLayout(5, 1));
        mainContainer.add(center, BorderLayout.CENTER);

        JPanel east = new JPanel();
        east.setBackground(Color.lightGray);
        mainContainer.add(east, BorderLayout.EAST);

        JPanel west = new JPanel();
        west.setBackground(Color.lightGray);
        mainContainer.add(west, BorderLayout.WEST);

        JPanel south = new JPanel();
        south.setBackground(Color.lightGray);
        mainContainer.add(south, BorderLayout.SOUTH);

        // 새 그룹 추가 버튼 - 클릭시 기존의 그릅 추가 버튼으로 이동
        JButton BTN_addGroup = new JButton();
        BTN_addGroup.setText("새 그룹 추가");
        BTN_addGroup.setSize(30, 30);
        center.add(BTN_addGroup);

        BTN_addGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                group_add();
                dispose();
            }
        });

        // 그룹 이름을 입력받습니다.
        JTextField groupname = new JTextField("그룹 이름", 10);
        groupname.setHorizontalAlignment(JTextField.CENTER);
        center.add(groupname);
        clear_txt(groupname);


        // 대표자 이름을 입력받습니다.
        JTextField leadername = new JTextField("대표자 이름", 10);
        leadername.setHorizontalAlignment(JTextField.CENTER);
        center.add(leadername);
        clear_txt(leadername);


        // 비밀번호를 입력받습니다.
        JTextField password = new JTextField("비밀번호 4자리", 10);
        password.setHorizontalAlignment(JTextField.CENTER);
        center.add(password);
        clear_txt(password);


        // 입력 확인 버튼 - 입력한 정보가 이미 존재하고 그 정보와 일치하면 group_main 화면 출력
        JButton BTN_accept = new JButton();
        BTN_accept.setText("입력 확인");
        BTN_accept.setSize(30, 30);
        center.add(BTN_accept);

        // 조건문 형식으로 입력한 정보와 일치하는지 판단 후에 일치하다면 group_main 화면 출력하는 기능 구현 필요
        BTN_accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                func_gui();
                dispose();
            }
        });

        setVisible(true);
    }

    public void func_gui() {
        // GUI 크기 및 타이틀 설정
        JFrame funcFrame = new JFrame();
        funcFrame.setSize(400, 200);
        funcFrame.setTitle("백신");
        funcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // container 설정
        Container funcContainer = funcFrame.getContentPane();
        funcContainer.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setLayout(new GridLayout(1,2));
        north.setBackground(Color.lightGray);
        funcContainer.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2,2,10,10));
        center.setBackground(Color.lightGray);
        funcContainer.add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setLayout(new GridLayout(1,2));
        south.setBackground(Color.lightGray);
        funcContainer.add(south, BorderLayout.SOUTH);

        ////////////////////////////////////////////////////////////////////////////
        // groupname과 leadername 정보를 파일에서 불러와주세요.
        // 추가가 완료 된 이후, 이 주석들은 모두 삭제해주세요.
        ////////////////////////////////////////////////////////////////////////////

        // 그룹 이름을 받아와서 출력
        JLabel groupname = new JLabel("그룹 이름: ");
        north.add(groupname);

        // 대표자 이름을 받아와서 출력
        JLabel leadername = new JLabel("대표자 이름: ");
        north.add(leadername);

        // 그룹 정보를 조회하는 버튼 관련 GUI 처리 코드
        JButton BTN_group_show = new JButton();
        BTN_group_show.setLocation(25, 70);
        BTN_group_show.setSize(150, 120);
        BTN_group_show.setText("그룹 정보 조회");
        BTN_group_show.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_group_show.setBackground(Color.gray);
        BTN_group_show.setOpaque(true);

        center.add(BTN_group_show);

        // 그룹 정보 출력 버튼을 클릭했을 때 해당 GUI로 이동함.
        BTN_group_show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                group_information();
                funcFrame.dispose();
            }
        });

        // 그룹 정보를 수정하는 버튼 관련 GUI 처리 코드
        JButton BTN_group_modify = new JButton();
        BTN_group_modify.setLocation(200, 70);
        BTN_group_modify.setSize(150, 120);
        BTN_group_modify.setText("그룹 정보 수정");
        BTN_group_modify.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_group_modify.setBackground(Color.gray);
        BTN_group_modify.setOpaque(true);

        center.add(BTN_group_modify);

        // 그룹 수정 버튼을 클릭했을 때 해당 GUI로 이동함.
        BTN_group_modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                group_modify();
                funcFrame.dispose();
            }
        });


        // 그룹을 완전 삭제하는 버튼 관련 GUI 처리 코드
        JButton BTN_group_delete = new JButton();
        BTN_group_delete.setLocation(25, 220);
        BTN_group_delete.setSize(150, 120);
        BTN_group_delete.setText("그룹 삭제");
        BTN_group_delete.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_group_delete.setBackground(Color.lightGray);
        BTN_group_delete.setOpaque(true);

        center.add(BTN_group_delete);

        // 그룹 삭제 버튼을 클릭했을 때 해당 GUI로 이동함.
        BTN_group_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                group_delete();
                funcFrame.dispose();
            }
        });

        // 최종 보고서를 출력하는 버튼 관련 GUI 처리 코드
        JButton BTN_report_print = new JButton();
        BTN_report_print.setLocation(200, 220);
        BTN_report_print.setSize(150, 120);
        BTN_report_print.setText("최종 보고서 출력");
        BTN_report_print.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_report_print.setBackground(Color.lightGray);
        BTN_report_print.setOpaque(true);

        center.add(BTN_report_print);

        // 최종 보고서 출력 버튼을 클릭했을 때 해당 GUI로 이동함.
        BTN_report_print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                report_print();
                funcFrame.dispose();
            }
        });

        JButton BTN_endprogram = new JButton();
        BTN_endprogram.setText("프로그램 종료하기");
        BTN_endprogram.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_endprogram.setBackground(Color.lightGray);
        BTN_endprogram.setOpaque(true);
        south.add(BTN_endprogram);

        BTN_endprogram.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                funcFrame.dispose();
            }
        });

        JButton BTN_goto_maingui = new JButton();
        BTN_goto_maingui.setText("이전 화면으로 돌아가기");
        BTN_goto_maingui.setFont(new Font("gothic", Font.BOLD, 15));
        BTN_goto_maingui.setBackground(Color.lightGray);
        BTN_goto_maingui.setOpaque(true);
        south.add(BTN_goto_maingui);

        BTN_goto_maingui.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                funcFrame.dispose();
                main_gui();
            }
        });

        funcFrame.setVisible(true);
    }

    public void group_add() {
        JFrame groupAddFrame = new JFrame();
        groupAddFrame.setSize(400, 200);
        groupAddFrame.setTitle("그룹 정보 입력 중");

        Container groupAddContainer = groupAddFrame.getContentPane();
        groupAddContainer.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(Color.lightGray);
        groupAddContainer.add(north, BorderLayout.NORTH);

        JPanel south = new JPanel();
        south.setBackground(Color.lightGray);
        groupAddContainer.add(south, BorderLayout.SOUTH);

        JPanel east = new JPanel();
        east.setBackground(Color.lightGray);
        groupAddContainer.add(east, BorderLayout.EAST);

        JPanel west = new JPanel();
        west.setBackground(Color.lightGray);
        groupAddContainer.add(west, BorderLayout.WEST);

        JPanel center = new JPanel();
        center.setBackground(Color.lightGray);
        center.setLayout(new GridLayout(5,1,5,5));
        groupAddContainer.add(center, BorderLayout.CENTER);

        JPanel center_south = new JPanel();
        center_south.setBackground(Color.lightGray);
        center_south.setLayout(new GridLayout(1,2));


        JTextField groupname = new JTextField("그룹 이름",6);
        groupname.setSize(10,20);
        center.add(groupname);
        clear_txt(groupname);

        // 이용 날짜를 입력받습니다.
        JTextField starttime = new JTextField("시작 시간",6);
        starttime.setSize(10,20);
        center.add(starttime);
        clear_txt(starttime);

        JTextField endtime = new JTextField("종료 시간",6);
        endtime.setSize(10,20);
        center.add(endtime);
        clear_txt(endtime);

        JTextField password = new JTextField("비밀번호 4자리 설정",6);
        password.setSize(10,20);
        center.add(password);
        clear_txt(password);

        center.add(center_south);

        JButton BTN_addgrouppeople = new JButton("그룹원 추가하기");
        center_south.add(BTN_addgrouppeople);

        BTN_addgrouppeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupAddFrame.dispose();
                people_add();

            }
        });
        JButton BTN_canclegroupadd = new JButton("취소하기");
        center_south.add(BTN_canclegroupadd);

        BTN_canclegroupadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupAddFrame.dispose();
                setVisible(true);

            }
        });
        groupAddFrame.setVisible(true);
        groupAddFrame.requestFocusInWindow();
    }

    // 그룹 추가를 담당하는 함수
    public void people_add() {
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

        // 그룹원 이름을 입력받습니다.
        JTextField person_name = new JTextField("그룹원 이름", 7);
        center.add(person_name);
        clear_txt(person_name);

        // 생년월일을 입력받습니다.
        JTextField birthday = new JTextField("생년 월일 6자리", 8);
        center.add(birthday);
        clear_txt(birthday);

        // 연락처를 입력받습니다.
        JTextField phoneNumber = new JTextField("전화번호(-제외)", 15);
        center.add(phoneNumber);
        clear_txt(phoneNumber);

        // 주소를 입력받습니다.
        JTextField address = new JTextField("거주중인 주소", 30);
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

        // arraylist 변수 그룹 생성
        ArrayList<GroupMember> group = new ArrayList<>();

        // check_list = {백신 접종 여부, 음성 확인서 여부}
        String[] check_list = {"null", "null"};

        // 백신 O 버튼을 눌렀을 시 리스너
        vaccine_yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check_list[0] = "O";
                check_list[1] = "해당X";
            }
        });
        // 백신 X 버튼을 눌렀을 시 리스너
        vaccine_no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check_list[0] = "X";
            }
        });
        // 음성 확인서 O 버튼을 눌렀을 시 리스너
        negative_yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check_list[1] = "O";
            }
        });
        // 음성 확인서 X 버튼을 눌렀을 시 리스너
        negative_no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check_list[1] = "X";
            }
        });

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

        ////////////////////////////////////////////////////////////////////////////
        // 비밀번호 4자리 생성 관련 TextField를 추가하였습니다.
        // 해당 기능과 관련하여 아래 구현하셨던 기능에 비밀번호 관련 기능을 추가해주세요.
        // 또한 그룹 추가 창을 요청하신대로 분리하였습니다. 아래 groupname과 time 관련해서 수정해주세요.
        // 이 코드는 에러 방지를 위해 작성된 "임의의" 코드입니다.
        JTextField groupname = new JTextField();
        JTextField starttime = new JTextField();
        JTextField endtime = new JTextField();
        //
        ////////////////////////////////////////////////////////////////////////////

        // 그룹원 저장하기
        JButton BTN_addperson = new JButton();
        BTN_addperson.setText("그룹원 추가하기");
        BTN_addperson.setSize(30,30);
        south.add(BTN_addperson, BorderLayout.WEST);

        // 입력이 완료되면 저장하고 문자열을 초기화해야 합니다.
        BTN_addperson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // 빈칸이 있을 경우 경고창 띄우기
                JFrame warning = new JFrame("정보를 입력하지 않았습니다.");
                JLabel showMessage = new JLabel();
                showMessage.setSize(new Dimension(50, 50));
                showMessage.setFont(new Font("gothic", Font.BOLD, 15));
                showMessage.setHorizontalAlignment(SwingConstants.CENTER);
                warning.add(showMessage);

                warning.setSize(400, 100);
                if (groupname.getText().length() < 1) {
                    showMessage.setText("그룹 이름 정보를 입력하세요.");
                    warning.setVisible(true);
                } else if (starttime.getText().length() < 1) {
                    showMessage.setText("시작시간 정보를 입력하세요.");
                    warning.setVisible(true);
                } else if (endtime.getText().length() < 1) {
                    showMessage.setText("종료시간 정보를 입력하세요.");
                    warning.setVisible(true);
                } else if (person_name.getText().length() < 1) {
                    showMessage.setText("그룹원 이름 정보를 입력하세요.");
                    warning.setVisible(true);
                } else if (birthday.getText().length() < 6) {
                    showMessage.setText("생년월일 정보를 입력하세요.");
                    warning.setVisible(true);
                } else if (phoneNumber.getText().length() <10){
                    showMessage.setText("핸드폰 번호를 입력하세요.");
                    warning.setVisible(true);
                } else if (address.getText().length() < 1) {
                    showMessage.setText("주소 정보를 입력하세요.");
                    warning.setVisible(true);
                } else {
                    String[] member = new String[9];
                    //그룹명, 회원 이름, 백신 접종 여부, 음성 확인서 여부, 시작 시간, 종료 시간, 생년월일, 핸드폰 번호, 주소
                    member[0] = groupname.getText();
                    member[1] = person_name.getText();
                    member[2] = check_list[0];
                    member[3] = check_list[1];
                    member[4] = starttime.getText();
                    member[5] = endtime.getText();
                    member[6] = birthday.getText();
                    member[7] = phoneNumber.getText();
                    member[8] = address.getText();
                    group.add(new GroupMember(member));

                    // 텍스트 초기화
                    person_name.setText("그룹원 이름");
                    birthday.setText("생년 월일 6자리를 입력하세요.");
                    address.setText("거주중인 주소를 입력하세요.");

                    negative.setVisible(false);
                    negative_yes.setVisible(false);
                    negative_no.setVisible(false);
                }
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
                // 그룹 정보 파일 만들기
                // 양식 : 그룹명, 대표자명
                String group_name = group.get(0).getGroupName();
                String agent_name = group.get(0).getPersonName();
                try (FileWriter fw = new FileWriter("그룹정보.txt", true)){ // 이어쓰기
                    fw.write(group_name + "," + agent_name + "\r\n");
                }catch(Exception err){
                    err.printStackTrace();
                }

                // 회원 정보 파일 만들기
                // 양식 : 그룹명, 회원 이름, 백신 접종 여부, 음성 확인서 여부, 시작 시간, 종료 시간, 생년월일, 주소
                try (FileWriter fw = new FileWriter(group.get(0).getGroupName() + ".txt", false)){ // 덮어쓰기
                    for (GroupMember member : group) {
                        for (int i = 0; i < member.all_info.length; i++) {
                            if (i == member.all_info.length -1)
                                fw.write(member.all_info[i]);
                            else
                                fw.write(member.all_info[i]+",");
                        }
                        fw.write("\r\n");
                    }
                }catch(Exception err){
                    err.printStackTrace();
                }

                // 창 닫기
                addFrame.dispose();
                main_gui();
            }
        });

        addContainer.setVisible(true);
        addFrame.setVisible(true);
        addContainer.requestFocusInWindow();
    }

    // TextField를 클릭했을 때 설명 문구 초기화 시키는 함수입니다.
    public void clear_txt(JTextField txt) {
        String basicString = txt.getText();
        txt.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                if(txt.getText().isEmpty()) {
                    txt.setText(basicString);
                }
            }
            public void focusGained(FocusEvent e) {
                if(txt.getText().equals(basicString)|| txt.getText().isEmpty()) {
                    txt.setText("");
                }
            }
        });
    }

    //그룹 정보 조회
    public void group_information() {
        JFrame infoFrame = new JFrame();
        infoFrame.setSize(800, 400);
        infoFrame.setTitle("그룹 정보를 입력하세요!");

        Container infoContainer = infoFrame.getContentPane();
        infoContainer.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(Color.lightGray);
        infoContainer.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.lightGray);
        infoContainer.add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setLayout(new BorderLayout());
        south.setBackground(Color.lightGray);
        infoContainer.add(south, BorderLayout.SOUTH);

        // GUI 상단에 대한 기능입니다.
        JTextField groupname = new JTextField("그룹 이름", 10);
        groupname.setSize(10,20);
        north.add(groupname);
        clear_txt(groupname);

        JTextField leadername = new JTextField("대표자 이름", 6);
        leadername.setSize(10,20);
        north.add(leadername);
        clear_txt(leadername);

        JTextField password = new JTextField("비밀번호 4자리", 9);
        password.setSize(10,20);
        north.add(password);
        clear_txt(password);


        JLabel starttime = new JLabel("이용 시작 시간: ");
        north.add(starttime);

        JLabel endtime = new JLabel("이용 종료 시간: ");
        north.add(endtime);

        // 그룹이름, 대표자 이름, 비밀번호가 일치하면 버튼을 눌렀을때 그룹원들의 정보를 조회가 가능하게 합니다.
        JButton BTN_show_information = new JButton();
        BTN_show_information.setText("조회하기");
        BTN_show_information.setSize(30,30);
        south.add(BTN_show_information, BorderLayout.WEST);

        BTN_show_information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gname = groupname.getText();
                String lname = leadername.getText();
                String pw = password.getText();

                //빈 칸이 있을 경우
                if (gname.length() < 1){
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("그룹명을 입력하세요.");
                    warning.setVisible(true);
                } else if(lname.length() < 1){
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("대표자 이름을 입력하세요.");
                    warning.setVisible(true);
                } else if(pw.length() < 4){
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("비밀번호를 입력하세요.");
                    warning.setVisible(true);
                } else {
                    // 파일 읽어와서 그룹정보 txt 파일과 비교
                    try (FileInputStream input = new FileInputStream(gname+".txt")){
                        //TODO 함수화
                        Scanner group_info = new Scanner(input);

                        ArrayList <String[]> tmp_members = new ArrayList<>();
                        while (group_info.hasNextLine()) {
                            String[] line = group_info.nextLine().split(",");
                            tmp_members.add(line);
                        }
                        // 테스트 출력
//                        for (String[] member : members) {
//                            for (String s : member) {
//                                System.out.println(s);
//                            }
//                        }
                        // arrayList -> array 변환
                        String[] header = {"이름", "생년월일", "연락처", "주소", "백신 접종", "음성 확인서"};
                        String[][] members = new String[tmp_members.size()][header.length];

                        for (int i = 0; i < tmp_members.size(); i++) {
                            for (int j = 0; j < header.length; j++) {
                                members[i][j] = tmp_members.get(i)[j];
                            }
                        }

                        starttime.setText(starttime.getText() + members[0][4]);
                        endtime.setText(endtime.getText() + members[0][5]);

                        DefaultTableModel model = new DefaultTableModel(members, header);
                        JTable showMembers = new JTable(model);
                        showMembers.setPreferredScrollableViewportSize(new Dimension(800, 200));

                        // 셀 수정을 불가능하게 합니다.
                        showMembers.setEnabled(false);

                        // column들을 이동시키는 것과 표의 크기를 조절하는 것을 불가능하게 합니다.
                        showMembers.getTableHeader().setReorderingAllowed(false);
                        showMembers.getTableHeader().setResizingAllowed(false);
                        //showMembers.setTableHeader(header);

                        // 텍스트에 가운데 정렬을 적용합니다.
                        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
                        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                        TableColumnModel tcm = showMembers.getColumnModel();

                        for (int i = 0; i < tcm.getColumnCount(); i++) {
                            tcm.getColumn(i).setCellRenderer(dtcr);
                        }

                        // 단, column의 크기를 지정합니다.
                        showMembers.getColumnModel().getColumn(0).setPreferredWidth(50);  // 이름 column
                        showMembers.getColumnModel().getColumn(1).setPreferredWidth(50);  // 생년월일 column
                        showMembers.getColumnModel().getColumn(2).setPreferredWidth(85);  // 연락처 column
                        showMembers.getColumnModel().getColumn(3).setPreferredWidth(300);  // 주소 column
                        showMembers.getColumnModel().getColumn(4).setPreferredWidth(50);  // 백신 접종 여부 column
                        showMembers.getColumnModel().getColumn(5).setPreferredWidth(70);  // 음성 확인서 여부 column

                        JScrollPane print = new JScrollPane(showMembers);
                        print.setPreferredSize(new Dimension(800, 100));
                        center.add(print);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });


        // 그룹원 정보 출력과 관련한 기능입니다.
        // 현재는 예시 데이터로 입력되어 있습니다. 이차원 배열의 형태로 값을 불러오도록 해주세요!!

        // 백신 접종 여부가 O일 경우에는 음성 확인서 접종 여부

        // GUI 하단에 대한 기능입니다.
        JButton BTN_end_information = new JButton();
        BTN_end_information.setText("조회 완료");
        BTN_end_information.setSize(30,30);
        south.add(BTN_end_information, BorderLayout.EAST);

        BTN_end_information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.dispose();
                func_gui();
            }
        });

        infoFrame.pack();
        infoFrame.setVisible(true);
        infoFrame.requestFocusInWindow();
    }

    // 그룹 수정을 담당하는 함수
    public void group_modify() {
        JFrame modiFrame = new JFrame();
        modiFrame.setSize(800, 400);
        modiFrame.setTitle("그룹 정보 수정");

        Container modiContainer = modiFrame.getContentPane();
        modiContainer.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(Color.lightGray);
        modiContainer.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.lightGray);
        modiContainer.add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setLayout(new BorderLayout());
        south.setBackground(Color.lightGray);
        modiContainer.add(south, BorderLayout.SOUTH);

        // GUI 상단에 대한 기능입니다. : 그룹 이름, 이용 시작 시간, 이용 종료 시간
        ////////////////////////////////////////////////////////////////////////////
        // groupname과 starttime, endtime의 정보를 파일에서 불러와주세요.
        // 00 그룹, 10:00, 19:00 이 적혀 있는 부분에 추가해주시면 됩니다.
        // 추가가 완료 된 이후, 이 주석들은 모두 삭제해주세요.
        ////////////////////////////////////////////////////////////////////////////
        JLabel groupnameInfo = new JLabel("그룹 이름 : ", 10);
        groupnameInfo.setSize(10,20);
        north.add(groupnameInfo);

        JLabel groupname = new JLabel("OO 그룹", 10);
        groupname.setSize(10,20);
        north.add(groupname);

        JLabel timeInfo = new JLabel("    이용 시간 : ", 10);
        timeInfo.setSize(10,20);
        north.add(timeInfo);

        JLabel starttime = new JLabel("10:00");
        north.add(starttime);

        JLabel justShow = new JLabel(" ~ ", 10);
        justShow.setSize(5,20);
        north.add(justShow);

        JLabel endtime = new JLabel("19:00");
        north.add(endtime);

        // GUI 중앙에 대한 기능입니다. : 그룹원 정보 출력
        ////////////////////////////////////////////////////////////////////////////
        // test data입니다. 파일에서 불러와 주세요.
        // 추가가 완료 된 이후, 이 주석들은 모두 삭제해주세요.
        ////////////////////////////////////////////////////////////////////////////
        String[] header = {"이름", "생년월일", "연락처", "주소", "백신 접종", "음성 확인서"};
        String[][] members = {{"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"}};

        DefaultTableModel model = new DefaultTableModel(members, header);
        JTable showMembers = new JTable(model);
        showMembers.setPreferredScrollableViewportSize(new Dimension(800, 200));

        // 셀 수정을 가능하게 합니다.
        showMembers.setEnabled(true);

        // column들을 이동시키는 것과 표의 크기를 조절하는 것을 불가능하게 합니다.
        showMembers.getTableHeader().setReorderingAllowed(false);
        showMembers.getTableHeader().setResizingAllowed(false);

        // 텍스트에 가운데 정렬을 적용합니다.
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tcm = showMembers.getColumnModel();
        for (int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }

        // Column의 크기를 지정합니다.
        showMembers.getColumnModel().getColumn(0).setPreferredWidth(50);  // 이름 column
        showMembers.getColumnModel().getColumn(1).setPreferredWidth(50);  // 생년월일 column
        showMembers.getColumnModel().getColumn(2).setPreferredWidth(85);  // 연락처 column
        showMembers.getColumnModel().getColumn(3).setPreferredWidth(300);  // 주소 column
        showMembers.getColumnModel().getColumn(4).setPreferredWidth(50);  // 백신 접종 여부 column
        showMembers.getColumnModel().getColumn(5).setPreferredWidth(70);  // 음성 확인서 여부 column

        JScrollPane print = new JScrollPane(showMembers);
        print.setPreferredSize(new Dimension(800, 100));
        center.add(print);

        // GUI 하단에 대한 기능입니다. : 수정 완료 버튼
        JButton BTN_deletePerson = new JButton();
        BTN_deletePerson.setText("그룹원 한 명 탈퇴");
        BTN_deletePerson.setSize(200,30);
        south.add(BTN_deletePerson, BorderLayout.EAST);
        BTN_deletePerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modiFrame.dispose();
                person_delete();
            }
        });


        JButton BTN_end_modify = new JButton();
        BTN_end_modify.setText("수정 완료");
        BTN_end_modify.setSize(30,30);
        south.add(BTN_end_modify, BorderLayout.EAST);

        BTN_end_modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modiFrame.dispose();
                func_gui();
            }
        });

        modiFrame.pack();
        modiFrame.setVisible(true);
        modiFrame.requestFocusInWindow();
    }

    // 그룹원 한 명의 탈퇴를 담당하는 함수
    public void person_delete() {
        JFrame deletePeople = new JFrame();
        deletePeople.setSize(400,100);

        Container delPeoContainer = deletePeople.getContentPane();
        delPeoContainer.setLayout(new BorderLayout());
        delPeoContainer.setBackground(Color.lightGray);

        JPanel north = new JPanel();
        north.setBackground(Color.lightGray);
        delPeoContainer.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.lightGray);
        delPeoContainer.add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setBackground(Color.lightGray);
        delPeoContainer.add(south, BorderLayout.SOUTH);

        // 탈퇴한 그룹원 이름을 입력 받습니다.
        JTextField personName = new JTextField("탈퇴한 그룹원 이름", 15);
        personName.setHorizontalAlignment(JTextField.CENTER);
        north.add(personName);
        clear_txt(personName);

        // 탈퇴한 그룹원 연락처를 입력 받습니다. (동명이인 방지)
        JTextField personPhone = new JTextField("탈퇴한 그룹원 전화번호", 15);
        personPhone.setHorizontalAlignment(JTextField.CENTER);
        center.add(personPhone);
        clear_txt(personPhone);

        JButton BTN_deleteProcess = new JButton("탈퇴 처리하기");
        BTN_deleteProcess.setSize(30,30);
        south.add(BTN_deleteProcess);

        // 탈퇴 처리 관련 액션 리스너
        BTN_deleteProcess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ////////////////////////////////////////////////////////////////////////////
                // 그룹원 한 명을 파일에서 완전히 삭제해주세요.
                // 추가가 완료 된 이후, 이 주석들은 모두 삭제해주세요.
                ////////////////////////////////////////////////////////////////////////////
                deletePeople.dispose();
                group_modify();
            }
        });

        // 취소 버튼
        JButton BTN_back = new JButton("취소");
        BTN_back.setSize(30,30);
        south.add(BTN_back);

        // 취소 관련 액션 리스너
        BTN_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePeople.dispose();
                group_modify();
            }
        });

        deletePeople.pack();
        deletePeople.setVisible(true);
        deletePeople.requestFocusInWindow();
    }

    // 그룹 삭제를 담당하는 함수
    public void group_delete() {

        JFrame deleteFrame = new JFrame();
        deleteFrame.setSize(300,200);
        deleteFrame.setTitle("그룹 삭제 중 . . .");

        Container delcontainer = deleteFrame.getContentPane();
        delcontainer.setLayout(new BorderLayout());
        delcontainer.setBackground(Color.lightGray);

        JPanel north = new JPanel();
        north.setBackground(Color.lightGray);
        delcontainer.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.lightGray);
        center.setLayout(new GridLayout(5,1));
        delcontainer.add(center, BorderLayout.CENTER);

        JPanel east = new JPanel();
        east.setBackground(Color.lightGray);
        delcontainer.add(east, BorderLayout.EAST);

        JPanel west = new JPanel();
        west.setBackground(Color.lightGray);
        delcontainer.add(west, BorderLayout.WEST);

        JPanel south = new JPanel();
        south.setBackground(Color.lightGray);
        delcontainer.add(south, BorderLayout.SOUTH);


        // 그룹 이름을 입력받습니다.
        JTextField groupname = new JTextField("그룹 이름", 10);
        groupname.setHorizontalAlignment(JTextField.CENTER);
        center.add(groupname);
        clear_txt(groupname);


        // 대표자 이름을 입력받습니다.
        JTextField leadername = new JTextField("대표자 이름", 10);
        leadername.setHorizontalAlignment(JTextField.CENTER);
        center.add(leadername);
        clear_txt(leadername);


        // 비밀번호를 입력받습니다.
        JTextField password = new JTextField("비밀번호 4자리", 10);
        password.setHorizontalAlignment(JTextField.CENTER);
        center.add(password);
        clear_txt(password);

        // 삭제하기 버튼
        JButton BTN_delete = new JButton();
        BTN_delete.setText("삭제하기");
        BTN_delete.setSize(30,30);
        center.add(BTN_delete);

        // 삭제하기 취소 버튼
        JButton BTN_cancel = new JButton();
        BTN_cancel.setText("취소하기");
        BTN_cancel.setSize(30,30);
        center.add(BTN_cancel);

        // 삭제하기 버튼 클릭 이벤트
        BTN_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gname = groupname.getText();
                String lname = leadername.getText();
                String pw = password.getText();

                try{
                    int index = 0;
                    // fileinputstream, scanner line 입력 받기 수정
                    FileReader fileReader = new FileReader("그룹정보.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String line = "";
                    while((line = bufferedReader.readLine()) != null){
                        String[] part = line.split(",");
                        // groupMember로 변환
                        // GroupMember gm = new GroupMember(line.split(","));
                        // gm.getName();
                        if(gname.equals(part[0]) && lname.equals(part[1])){
                            JFrame deleteCheckFrame = new JFrame();
                            deleteCheckFrame.setSize(280,110);
                            deleteCheckFrame.setTitle("그룹 삭제");
                            Container delCheckCon = deleteCheckFrame.getContentPane();
                            delCheckCon.setLayout(new BorderLayout());
                            delCheckCon.setBackground(Color.lightGray);

                            JPanel center = new JPanel();
                            center.setBackground(Color.lightGray);
                            delCheckCon.add(center, BorderLayout.CENTER);

                            JLabel really = new JLabel("정말 삭제하시겠습니까?");
                            really.setSize(280, 50);
                            really.setFont(new Font("gothic", Font.BOLD, 20));
                            center.add(really);

                            JButton BTN_yes = new JButton("네");
                            BTN_yes.setSize(30,30);
                            center.add(BTN_yes);
                            BTN_yes.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    deleteCheckFrame.dispose();
                                    deleteFrame.dispose();
                                    // 파일이 삭제되는 코드를 추가해주세요!!
                                    String filename = gname + ".txt";
                                    //그룹 정보 파일 읽고
                                    try {
                                        Files.delete(Path.of(filename));
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }

                                }
                            });

                            JButton BTN_no = new JButton("아니오");
                            BTN_no.setSize(30,30);
                            center.add(BTN_no);
                            BTN_no.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    deleteCheckFrame.dispose();
                                }
                            });
                            deleteCheckFrame.setVisible(true);
                            break;

                        }else{
                            index ++;
                            deleteFrame.setTitle("입력 " + index + "회 오류입니다.");
                            groupname.setText("그룹 이름");
                            leadername.setText("대표자 이름");
                            password.setText("비밀번호 4자리");
                        }
                    }

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                //
                // 조건문으로 삭제 조건(그룹 이름, 대표자 이름, 비밀번호가 모두 맞는지를 확인하는 코드 작성 부탁드립니다!!
                //
                // 조건이 틀렸다면 아래의 코드를 실행해주세요. index 추가 코드도 작성해주세요.
//                deleteFrame.setTitle("입력 " + index + "회 오류입니다.");
//                groupname.setText("그룹 이름");
//                leadername.setText("대표자 이름");
//                password.setText("비밀번호 4자리");
                // 조건이 맞았다면 아래의 코드를 실행해주세요.
                JFrame deleteCheckFrame = new JFrame();
                deleteCheckFrame.setSize(280,110);
                deleteCheckFrame.setTitle("그룹 삭제");
                Container delCheckCon = deleteCheckFrame.getContentPane();
                delCheckCon.setLayout(new BorderLayout());
                delCheckCon.setBackground(Color.lightGray);

                JPanel center = new JPanel();
                center.setBackground(Color.lightGray);
                delCheckCon.add(center, BorderLayout.CENTER);

                JLabel really = new JLabel("정말 삭제하시겠습니까?");
                really.setSize(280, 50);
                really.setFont(new Font("gothic", Font.BOLD, 20));
                center.add(really);

                JButton BTN_yes = new JButton("네");
                BTN_yes.setSize(30,30);
                center.add(BTN_yes);
                BTN_yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteCheckFrame.dispose();
                        deleteFrame.dispose();
                        func_gui();
                    }
                });

                JButton BTN_no = new JButton("아니오");
                BTN_no.setSize(30,30);
                center.add(BTN_no);
                BTN_no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteCheckFrame.dispose();
                    }
                });
                deleteCheckFrame.setVisible(true);
            }
        });

        // 삭제 취소 버튼을 누를시 그룹 삭제 창이 사라지게 하는 액션리스너
        BTN_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFrame.dispose();
                func_gui();
            }
        });


        deleteFrame.setVisible(true);
        deleteFrame.requestFocusInWindow();
    }

    // 최종 보고서를 출력하는 함수
    public void report_print() {
        JFrame printFrame = new JFrame();
        printFrame.setTitle("최종 보고서 출력");

        Container printContainer = printFrame.getContentPane();
        printContainer.setLayout(new BorderLayout());
        printContainer.setBackground(Color.lightGray);

        JPanel north = new JPanel();
        north.setBackground(Color.lightGray);
        north.setLayout(new GridLayout(1,4));
        printContainer.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.lightGray);
        center.setLayout(new BorderLayout());
        printContainer.add(center, BorderLayout.CENTER);

        JPanel center_north = new JPanel();
        center_north.setBackground(Color.lightGray);
        center_north.setLayout(new BorderLayout());
        center.add(center_north, BorderLayout.NORTH);

        JPanel south = new JPanel();
        south.setBackground(Color.lightGray);
        printContainer.add(south, BorderLayout.SOUTH);

        JLabel groupname = new JLabel("그룹이름 : ");
        groupname.setBackground(Color.white);
        groupname.setOpaque(true);
        north.add(groupname);

        JLabel leadername = new JLabel("대표자 이름 : ");
        north.add(leadername);

        JLabel starttime = new JLabel("이용 시작 시간 : ");
        north.add(starttime);

        JLabel endtime = new JLabel("이용 마침 시간 : ");
        north.add(endtime);

        ////////////////////////////////////////////////////////////////////////////
        // 현행 규정과 통계 관련해서 코드를 모두 작성하고 변수에 알맞게 대입해주세요.
        // 모임이 가능한지와 불가능한지 또한 판정하는 코드를 작성 후 변수에 대입해주세요.
        // 추가가 완료 된 이후, 이 주석들은 모두 삭제해주세요.
        ////////////////////////////////////////////////////////////////////////////

        //아래 str1에 현행 규정 입력하기 <br>은 줄바꿈 문자임
        String str1 = "================현행규정================<br>"+ "거리두기 ~~단계<br>"+"운영시간 - 제한 없음<br>"+ "테이블간 거리두기 ~~~<br>"+ "최대 ~인 제한<br>";

        JLabel rule = new JLabel("<HTML><body style ='text-align:center;'>"+str1 +"</body></HTML>",JLabel.CENTER);
        center_north.add(rule,BorderLayout.NORTH);

        //백신 접종 통계를 위해 아래 변수에 수 집어넣기
        int vaccine_O = 0;
        int vaccine_X = 0;
        int negative = 0;

        JLabel statistics = new JLabel("<HTML><body style ='text-align:center;'>"+"================통계자료================<br>"
                +"(백신 접종 여부 : O("+vaccine_O+"명), X("+vaccine_X+"명/ 음성확인서 : "+negative+") " +"</body></HTML>", JLabel.CENTER);
        center_north.add(statistics,BorderLayout.CENTER);

        // 현행 규정과 비교해서 현재 그룹의 모임이 가능한지 불가능한지를 판정 후 출력
        String finalJudgement = "※최종 판정 :";
        JLabel judgement = new JLabel(finalJudgement, JLabel.CENTER);
        center_north.add(judgement,BorderLayout.SOUTH);


        // GUI 중앙에 대한 기능입니다. : 그룹원 정보 출력
        ////////////////////////////////////////////////////////////////////////////
        // test data입니다. 파일에서 불러와 주세요.
        // 추가가 완료 된 이후, 이 주석들은 모두 삭제해주세요.
        ////////////////////////////////////////////////////////////////////////////
        String[] header = {"이름", "생년월일", "연락처", "주소", "백신 접종", "음성 확인서"};
        String[][] members = {{"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"},
                {"김단국", "020202", "01012345678", "단국대", "O", "해당 사항X"}};

        DefaultTableModel model = new DefaultTableModel(members, header);
        JTable showMembers = new JTable(model);
        showMembers.setPreferredScrollableViewportSize(new Dimension(800, members.length * 17));
        printFrame.setSize(800,250 + members.length * 17);

        // 셀 수정을 불가능하게 합니다.
        showMembers.setEnabled(false);

        // column들을 이동시키는 것과 표의 크기를 조절하는 것을 불가능하게 합니다.
        showMembers.getTableHeader().setReorderingAllowed(false);
        showMembers.getTableHeader().setResizingAllowed(false);

        // 텍스트에 가운데 정렬을 적용합니다.
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tcm = showMembers.getColumnModel();
        for (int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }

        // Column의 크기를 지정합니다.
        showMembers.getColumnModel().getColumn(0).setPreferredWidth(50);  // 이름 column
        showMembers.getColumnModel().getColumn(1).setPreferredWidth(50);  // 생년월일 column
        showMembers.getColumnModel().getColumn(2).setPreferredWidth(85);  // 연락처 column
        showMembers.getColumnModel().getColumn(3).setPreferredWidth(300);  // 주소 column
        showMembers.getColumnModel().getColumn(4).setPreferredWidth(50);  // 백신 접종 여부 column
        showMembers.getColumnModel().getColumn(5).setPreferredWidth(70);  // 음성 확인서 여부 column
        showMembers.setRowHeight(17);

        JScrollPane print = new JScrollPane(showMembers);
        print.setPreferredSize(new Dimension(800, members.length * 17));
        center.add(print);


        // GUI 하단 : 인쇄하기 버튼과 창 닫기 버튼
        JButton BTN_print = new JButton("인쇄하기");
        BTN_print.setSize(30,30);
        south.add(BTN_print);

        BTN_print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ////////////////////////////////////////////////////////////////////////////
                // 실제 인쇄 관련한 코드를 작성해주시면 됩니다.
                // 구글에 Java 인쇄 기능 등의 키워드를 입력 후 검색하시면 관련 코드들 나옵니다.
                // 해당 코드들 복사한 후 적절하게 수정하여 실행이 되도록 해주시면 됩니다.
                // 예시 사이트 : https://dinae.tistory.com/24
                ////////////////////////////////////////////////////////////////////////////
            }
        });

        JButton BTN_close_print = new JButton("창 닫기");
        BTN_print.setSize(30,30);
        south.add(BTN_close_print);

        BTN_close_print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printFrame.dispose();
                func_gui();
            }
        });

        printFrame.setVisible(true);
        printFrame.requestFocusInWindow();
    }
}