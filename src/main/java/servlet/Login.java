package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // リクエストパラメータの取得
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");
    // Userインスタンス（ユーザー情報）の生成
    User user = new User(name, pass);
    // ログイン処理
    LoginLogic loginLogic = new LoginLogic();
    boolean isLogin = loginLogic.execute(user);

    // ログイン成功時の処理
    if (isLogin) {
      // ユーザー情報をセッションスコープに保存
      HttpSession session = request.getSession();
      session.setAttribute("loginUser", user);
    }
    // ログイン結果画面にフォワード
    RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/loginResult.jsp");
    dispatcher.forward(request, response);
  }
}