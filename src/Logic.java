import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/* 회원 정보 생성 기능 C */
// 그룹 정보 생성 기능 C
// 그룹 정보 조회 기능 R
// 그룹 정보 수정 기능 U
// 그룹 정보 삭제 기능 D
public class Logic {
    // 그룹 정보 텍스트 파일 생성
    public void createGroupInfo(String group_name, String start_time, String end_time, String password) {
        try (FileWriter fw = new FileWriter("그룹정보.txt", true)){ // 이어쓰기
            fw.write(group_name + "," + start_time + "," + end_time + "," + password + "\r\n");
        } catch(Exception err){
            err.printStackTrace();
        }
    }

    // 회원 정보 텍스트 파일 생성
    public void createMemberInfobyArray(String group_name, ArrayList<String[]> members) {
        try (FileWriter fw = new FileWriter(group_name + ".txt", false)){ // 덮어쓰기
            for (String[] member : members) {
                for (int i = 0; i < member.length; i++) {
                    if (i == member.length -1)
                        fw.write(member[i]);
                    else
                        fw.write(member[i]+",");
                }
                fw.write("\r\n");
            }
        } catch(Exception err){
            err.printStackTrace();
        }
    }

    public void createMemberInfobyString(String group_name, ArrayList<String> members) {
        try (FileWriter fw = new FileWriter(group_name + ".txt", false)){ // 덮어쓰기
            for (String member : members) {
                fw.write(member);
                fw.write("\r\n");
            }
        } catch(Exception err){
            err.printStackTrace();
        }
    }
    // 그룹 수정 시 회원 추가
    public void updateMemberInfo(String group_name, ArrayList<String[]> members) {
        try (FileWriter fw = new FileWriter(group_name + ".txt", true)){ // 이어쓰기
            for (String[] member : members) {
                for (int i = 0; i < member.length; i++) {
                    if (i == member.length -1)
                        fw.write(member[i]);
                    else
                        fw.write(member[i]+",");
                }
                fw.write("\r\n");
            }
        } catch(Exception err){
            err.printStackTrace();
        }
    }

    // 그룹 정보 조회하기
    String[] group_info;
    public String[] getGroupInfo(String group_name, String password) {
        try (FileInputStream input = new FileInputStream("그룹정보.txt")) {
            Scanner group_txt = new Scanner(input);

            while (group_txt.hasNextLine()) {
                String[] line = group_txt.nextLine().split(",");
//                System.out.println(line[3]+" "+ password);
                if (Objects.equals(line[0], group_name) && Objects.equals(line[3], password)) {
                    group_info = line;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return group_info;
    }

    // 이용시간 정보 가져오기
    String[] time_info;
    public String[] getTimeInfo(String group_name) {
        try (FileInputStream input = new FileInputStream("그룹정보.txt")) {
            Scanner group_txt = new Scanner(input);

            while (group_txt.hasNextLine()) {
                String[] line = group_txt.nextLine().split(",");
                if (Objects.equals(line[0], group_name)) {
                    time_info = Arrays.copyOfRange(line, 1, 3);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return time_info;
    }

    // 회원 정보 조회하기
    String[][] members;
    public String[][] getMemberInfo(String group_name, String[] header) {
        try (FileInputStream input = new FileInputStream(group_name+".txt")) {
            Scanner group_info = new Scanner(input);

            // arrayList에 member 정보 추가하기
            ArrayList<String[]> tmp_members = new ArrayList<>();
            while (group_info.hasNextLine()) {
                String[] line = group_info.nextLine().split(",");
                tmp_members.add(line);
            }

            // arrayList -> array 변환
            members = new String[tmp_members.size()][header.length];

            for (int i = 0; i < tmp_members.size(); i++) {
                for (int j = 0; j < header.length; j++) {
                    members[i][j] = tmp_members.get(i)[j];
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
//            infoFrame.setTitle("파일을 찾을 수 없습니다.");
            // throw Error
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return members;
    }

    // 대표자명 가져오기
    String agent_name;
    public String getAgent_name(String group_name) throws FileNotFoundException {
        try (FileInputStream input = new FileInputStream(group_name+".txt")) {
            Scanner lines = new Scanner(input);
            agent_name = lines.nextLine().split(",")[0];
        } catch (FileNotFoundException e){
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agent_name;
    }

    // 회원 파일 자체를 삭제
    public void deleteMemberinfo(String gname){
        String groupName = gname + ".txt";
        try {
            Files.delete(Path.of(groupName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //그룹 정보 파일에서 삭제
    public void deleteGroupinfo(String gname){

        String newGroupinfo = "";
        try(FileInputStream input = new FileInputStream("그룹정보.txt")){

            Scanner sc = new Scanner(input);

            while (sc.hasNextLine()){
                String nextLine = sc.nextLine();
                String[] splitLine = nextLine.split(",");

                if(!splitLine[0].equals(gname)){
                    newGroupinfo += nextLine + "\n";
                }
            }

            try( FileWriter fileWriter = new FileWriter("그룹정보.txt",false)){
                fileWriter.write(newGroupinfo);
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    // parameter
    // level, facility, vaccine O/X, starttime, endtime
    // String[] : 이름, 생년월일, 연락처, 주소, 백신접종, 음성확인서
    // String groupName,

    // 한 사람씩 enter possible 여부를 확인
    public boolean enter_judgement(String level, String facility, String[][] members, String groupName) {

        String[][] member = members;
        int covid = Integer.parseInt(level);

        String place = facility;

        //member[2]~[3]이 없어서 임시로 지정
        String starttime = this.getTimeInfo(groupName)[0];
        String endtime = this.getTimeInfo(groupName)[1];

        String[] sTime = starttime.split(":");

        int sHour = Integer.parseInt(sTime[0]);
        int sMin = Integer.parseInt(sTime[1]);

        String[] eTime = endtime.split(":");
        int eHour = Integer.parseInt(eTime[0]);
        int eMin = Integer.parseInt(eTime[1]);

        boolean possible = true;

        for (String[] strings : member) {
            if (covid == 1) {
                possible = true;
            } else if (covid == 2) {
                if (place == "식당") { //24시 이후 입장 불가
                    if ((sHour >= 7 && sHour <= 23) && ((eHour >= 7 && eHour <= 23) || (eHour == 24 && eMin == 0))) {
                        possible = true;
                    } else {
                        possible = false;
                        break;
                    }
                } else { //제한없음
                    possible = true;
                }
            } else if (covid == 3) {
                if (place == "식당") { //22시 이후 입장불가
                    if ((sHour >= 7 && sHour <= 21) && ((eHour >= 7 && eHour <= 21) || (eHour == 22 && eMin == 0))) {
                        possible = true;
                    } else {
                        possible = false;
                        break;
                    }
                } else { //제한없음
                    possible = true;
                }
            } else {
                if (place == "식당") { //21시 이후 입장불가, 백신패스o-> 18시 이후 4인까지
                    if ((sHour >= 7 && sHour < 21) && ((eHour >= 7 && eHour < 21) || (eHour == 21 && eMin == 0))) {
                        if (sHour < 18 && ((eHour < 18) || (eHour == 18 && eMin == 0))) {
                            possible = true;
                        } else {
                            if (strings[4] == "O") {
                                if (member.length <= 4) {
                                    possible = true;
                                } else {
                                    possible = false;
                                    break;
                                }
                            } else {
                                if (member.length <= 2) {
                                    //그룹인원수 어떻게 하는지
                                    possible = true;
                                } else {
                                    possible = false;
                                    break;
                                }
                            }
                        }
                    } else {
                        possible = false;
                        break;
                    }
                }
                else { // 22시 이후 운영제한
                    if ((sHour >= 7 && sHour < 22) && ((eHour >= 7 && eHour < 22) || (eHour == 22 && eMin == 0))) {
                        possible = true;
                    } else {
                        possible = false;
                        break;
                    }
                }
            }
        }
        System.out.println(possible);
        return possible;
    }
}