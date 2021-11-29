import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


public class GUI extends JFrame {
    public void main_gui() {
        // Main GUI 크기 및 타이틀 설정
        setSize(400, 400);
        setTitle("백신");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        // 그룹 정보 출력 버튼을 클릭했을 때 해당 GUI로 이동함.
        BTN_group_show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                group_information();
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

        // 그룹 삭제 버튼을 클릭했을 때 해당 GUI로 이동함.
        BTN_group_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                group_delete();
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
                if (groupname.getText().length() < 1) {
//                    BTN_addperson.setText("정보 모두 입력!!");
                    // 경고창 변경 필요
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("그룹원 정보를 입력하세요.");
                    warning.setVisible(true);
//                    BTN_addperson.setText("그룹원 추가하기");
                } else if (starttime.getText().length() < 1) {
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("시작시간 정보를 입력하세요.");
                    warning.setVisible(true);
                } else if (endtime.getText().length() < 1) {
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("종료시간 정보를 입력하세요.");
                    warning.setVisible(true);
                } else if (person_name.getText().length() < 1) {
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("그룹원명 정보를 입력하세요.");
                    warning.setVisible(true);
                } else if (birthday.getText().length() < 6) {
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("생년월일 정보를 입력하세요.");
                    warning.setVisible(true);
                } else if (phoneNumber.getText().length() <10){
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("핸드폰 번호를 입력하세요.");
                    warning.setVisible(true);
                } else if (address.getText().length() < 1) {
                    JFrame warning = new JFrame();
                    warning.setSize(400, 10);
                    warning.setTitle("주소 정보를 입력하세요.");
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
            }
        });

        infoFrame.pack();
        infoFrame.setVisible(true);

    }

    // 그룹 수정을 담당하는 함수
    public void group_modify() {

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
                        //
                        // 파일이 삭제되는 코드를 추가해주세요!!
                        //
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
            }
        });


        deleteFrame.setVisible(true);
    }

    // 최종 보고서를 출력하는 함수
    public void report_print() {

    }

}