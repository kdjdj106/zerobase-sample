package db;

import java.util.Scanner;

public class DbTestMain {

    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.list();
    }

    public static void _main(String[] args) {
        MemberService memberService = new MemberService();

        Scanner scanner = new Scanner(System.in);

        String memberType = "email";
        System.out.println("아이디입력:>>>");
        String userId = scanner.next();
        System.out.println("비밀번호 입력 :>>>");
        String passwordValue = scanner.next();
        System.out.println("이름입력 :>>>");
        String name = scanner.next();

        Member member = new Member();
        member.setMemberType(memberType);
        member.setUserId(userId);
        member.setPassword(passwordValue);
        member.setName(name);



        memberService.register(member);


        memberService.list();
    }
}
