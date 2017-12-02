package controller;

import bean.Message;
import bean.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfServer extends Remote {

    <T> T execute(Task<T> t) throws RemoteException;

    //trả về địa chỉ ip của server
    String getIP() throws RemoteException;

    // kiểm tra có còn kết nối, trả về nothing
    void CheckConnection() throws RemoteException;

    //thêm 1 client server mới và list
    boolean AddServer(int id,InterfServer server) throws RemoteException;

    // xóa một client server ra khỏi list
    boolean RemoveServer(int idServer) throws RemoteException;

    //đẩy 1 message vào
    boolean PushMessage(Message message) throws RemoteException;

    //cài đặt giá trị id của server
    void setId(int id) throws RemoteException;

    // lấy thông tin id của server
    int getID() throws RemoteException;
}
