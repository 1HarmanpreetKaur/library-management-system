package com.library.library_management;

public class MemberLinkedList {

    private class MemberNode {
        Members data;
        MemberNode next;

        public MemberNode (Members data) {
            this.data = data;
            this.next = null;
        }
    }

    private MemberNode head;

    public MemberLinkedList() {
        head = null;
    }

    //Add new member to the end of the list
    public void addMember(Members m) {        //O(n)
        MemberNode newNode = new MemberNode(m);

        //If list is empty
        if (head == null) {
            head = newNode;
            System.out.println("Member added successfully.");
            return;
        }

        //If list is not empty
        MemberNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        System.out.println("Member added successfully");
    }

    //Delete a member using memberId
    public void deleteMember(int memberId) {     //O(n)

        //If list is empty
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        //If first node is the one to delete
        if (head.data.getMemberId() == memberId) {
            head = head.next;
            System.out.println("Member deleted successfully.");
            return;
        }

        //If member is somewhere in the list (not at first)
        MemberNode current = head;
        while (current.next != null && current.next.data.getMemberId() != memberId) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Member not found.");
        }
        else {
            current.next = current.next.next;
            System.out.println("Member deleted successfully.");
        }
    }

    //Search for a member by memberId
    public Members searchMember(int memberId) {       //O(n)
        MemberNode current = head;

        while (current != null) {
            if (current.data.getMemberId() == memberId) {
                return current.data;
            }
            current = current.next;
        }

        return null;
    }

    //Search for a member by Member's name
    public void searchMemberByName(String name) {         //O(n)

        if (head == null) {
            System.out.println("No members found.");
            return;
        }

        MemberNode current = head;
        boolean found = false;

        while (current != null) {

            if (current.data.getName().equalsIgnoreCase(name)) {
                current.data.displayMember();
                found = true;
            }

            current = current.next;
        }

        if (!found) {
            System.out.println("No member found with that name.");
        }
    }

    //To update member info using memberId         O(n)
    public void updateMember(int memberId, String name, String email, String phone) {
        MemberNode current = head;

        while (current != null) {
            if (current.data.getMemberId() == memberId) {
                current.data.updateMemberInfo(name, email, phone);
                System.out.println("Member updated successfully.");
                return;
            }

            current = current.next;
        }

        System.out.println("Member not found.");
    }

    //Display all members      //O(n)
    public void displayMembers() {

        if (head == null) {
            System.out.println("No members found.");
            return;
        }

        MemberNode current = head;

        while (current != null) {
            current.data.displayMember();
            current = current.next;
        }
    }

    public java.util.List<Members> getAllMembers() {
        java.util.List<Members> result = new java.util.ArrayList<>();
        MemberNode current = head;
        while (current != null) {
            result.add(current.data);
            current = current.next;
        }
        return result;
    }
}
