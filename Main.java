public class Main{
    public static void main(String[] args) {
        linkList list1=new linkList(10);
        list1.addSortMinMax(15);
        list1.addSortMinMax(8);
        list1.addSortMinMax(20);
        list1.showAll();
    }
}
class node{
    int num;
    node prev;
    node next;

    node(int numIn){
        num=numIn;
        prev=null;
        next=null;
    }
}
class linkList{
    node head;
    node tail;
    node nullNode;
    int amount;

    linkList(int numIn){
        head=new node(numIn);
        tail=head;
        nullNode=new node(-1);
        amount=1;
    }
    void connectNode(node n1, node n2){
        n1.next=n2;
        n2.prev=n1;
    }
    void addFirst(int newNum){
        node newNode=new node(newNum);
        connectNode(newNode, head);
        head=newNode;
        amount++;
    }
    void addLast(int newNum){
        node newNode=new node(newNum);
        connectNode(tail, newNode);
        tail=newNode;
    }
    void showHead(node currNode){
        if(currNode==null){
            System.out.print(" End.");
        }
        else{
            System.out.printf("%d ", currNode.num);
            showHead(currNode.next);
        }
    }
    void showTail(node currNode){
        if(currNode==null){
            System.out.print(" End.");
        }
        else{
            System.out.printf("%d ", currNode.num);
            showTail(currNode.prev);
        }
    }
    void showAll(){
        showHead(head);
        System.out.println("");
        showTail(tail);
    }
    node findNumAtHead(node currNode, int numIn){
        if(currNode==null){
            return nullNode;
        }
        else{
            if(currNode.num==numIn){
                return currNode;
            }
            else{
                return findNumAtHead(currNode.next, numIn);
            }
        }
    }
    node findNumAtTail(node currNode, int numIn){
        if(currNode==null){
            return nullNode;
        }
        else{
            if(currNode.num==numIn){
                return currNode;
            }
            else{
                return findNumAtTail(currNode.prev, numIn);
            }
        }
    }
    node findPos(node currNode, int currPos, int pos){
        if(currPos<amount){
            return tail;
        }
        else{
            if(currPos==pos){
                return currNode;
            }
            else{
                return findPos(currNode.next, currPos+1, pos);
            }
        }
    }
    node findNum(int numIn){
        node midNode=findPos(head, 1, amount/2);
        if(numIn<midNode.num){
            return findNumAtHead(head, numIn);
        }
        else if(numIn>midNode.num){
            return findNumAtTail(tail, numIn);
        }
        else{
            return nullNode;
        }
    }
    void addSortMinMax(int newNum){
        node newNode=new node(newNum);
        node compNode=head;
        while(compNode!=null){
            if(newNode.num<compNode.num){
                addFirst(newNum);
                break;
            }
            else{
                if((compNode.num<newNum)&&(compNode.next.num>newNum)){
                    connectNode(newNode, compNode.next);
                    connectNode(compNode, newNode);
                    break;
                }
                else{
                    compNode=compNode.next;
                }
            }
        }
    }
    void addSortMaxMin(int newNum){
        node newNode=new node(newNum);
        node compNode=head;
        while(compNode!=null){
            if(newNum>compNode.num){
                addFirst(newNum);
                break;
            }
            else{
                if((compNode.num<newNum)&&(compNode.next.num>newNum)){
                    connectNode(compNode, newNode);
                    connectNode(newNode, compNode);
                    break;
                }
                else{
                    compNode=compNode.next;
                }
            }
        }
    }
    void deleteNum(int numIn){
        node delNode=findNum(numIn);
        if(delNode==head){
            head=head.next;
            head.prev=null;
            delNode.next=null;
        }
        else if(delNode==tail){
            tail=tail.prev;
            tail.next=null;
            delNode.next=null;
        }
        else{
            connectNode(delNode.prev, delNode.next);
            delNode.prev=null;
            delNode.next=null;
        }
    }
}