package pbookStepcomplete;

public class Properties {

    static String menu_EX1 = "====== 미니 전화번호부 ======\n"
            + "1. 전화번호부 전체보기\n"
            + "2. 전화번호부 입력하기\n"
            + "3. 전화번호부 지우기\n"
            + "4. 전화번호부 수정하기\n "
            + "======================\n";

    static String menu2_name = "===== 전화번호 입력모드 =====\n"
            + "이름 입력 후 엔터를 누르세요\n ";
    static String menu2_phone = "전화번호 입력 후 엔터를 누르세요\n ";
    static String menu2_add = "입력이 완료되었습니다\n "
            + "메뉴를 선택하세요\n ";
    static String menu2_duplicate = "동일한 이름과 전화번호가 존재합니다\n "
            + "입력에 실패하였습니다\n "
            + "메뉴를 선택하세요\n ";

    // 전화번호만 다르면 이름만 같은 모드로 바꾼 후 사용자에게 printDisplay 이름이 중복인데 저장할 것인지 물어본후
    //  저장한다고 하면 menu2_force()에서 저장하고 메뉴입력모드로 돌아가고
    //  저장안한다고 menu2_no()에서하면 printDisplay에 입력을 취소하였다고 하고 메뉴입력으로 돌아간다

    static String menu2_duplicate2 = "전화번호는 다르지만 같은 이름이 존재합니다\n "
            + "그래도 입력하시겠습니까? (y/n)\n ";

    static String menu2_no = "입력을 취소하였습니다\n "
            + "메뉴를 선택하세요\n ";


    static String menu2_duplicate3 = "동일한 전화번호가 존재합니다\n "
            + "입력에 실패하였습니다\n "
            + "메뉴를 선택하세요\n ";


    static String menu3_name = "===== 전화번호 삭제모드 =====\n"
            + "이름 입력 후 엔터를 누르세요\n ";
    static String menu3_del = "삭제가 완료되었습니다\n "
            + "메뉴를 선택하세요\n ";
    static String menu3_delno = "해당 이름의 전화번호가 없습니다.\n "
            + "삭제가 실패하였습니다.\n"
            + "메뉴를 선택하세요\n ";

    static String menu4_name = "===== 전화번호 수정모드 =====\n"
            + "이름 입력 후 엔터를 누르세요\n ";
    static String menu4_revise = "수정이 완료되었습니다\n "
            + "메뉴를 선택하세요\n ";
    static String menu4_reviseno = "해당 이름의 전화번호가 없습니다.\n "
            + "수정이 실패하였습니다.\n"
            + "메뉴를 선택하세요\n ";

    static String menu4_phone2 = "수정할 번호 선택 후 엔터를 누르세요\n";
    static String menu4_phone = "변경할 전화번호 입력 후 엔터를 누르세요\n ";


    static String file_PATH = "/Users/hyunwoo/Projects/phonebook.txt";
}
