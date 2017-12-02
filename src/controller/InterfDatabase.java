package controller;

import bean.Task;
import bean.Ticket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;


public interface InterfDatabase extends Remote {

    <T> T execute(Task<T> t) throws RemoteException;

    //lấy địa chỉ ip database
    String getIP() throws RemoteException;

    //thực hiện việc cập nhật database
    int UpdateTicket(Ticket ticket) throws RemoteException;

    //lấy danh sách ticket trong database
    ArrayList<Ticket> getTicketLists() throws RemoteException;

    //lấy danh sách kết nối hiện tại
    HashMap<Integer,InterfServer>  getServerLists() throws RemoteException;

    // thực hiện đăng ký việc join vào
    boolean Register(InterfServer server) throws RemoteException;

    //thực hiện việc hủy đăng ký
    boolean UnRegis(int idServer) throws RemoteException;

    // thực hiện việc kiểm tra kết nối với server tới database, return nothing
    void CheckConnection() throws RemoteException;

}
