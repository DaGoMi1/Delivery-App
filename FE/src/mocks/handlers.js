import {http, HttpResponse} from 'msw';
const mockUser = [];

let accessToken = "mock_access_token";
let refreshToken = "mock_refresh_token";

export const handlers = [
  // 회원가입
  http.post("/member/register", async(req) => {
    const {userId, password, password2, name, phone, email} = await req.json();

    if(password !== password2) {
      return HttpResponse.json({message: "비밀번호를 확인해주세요."}, {status : 400})
    }

    const newUser = {id: mockUser.length+1, userId, password, name, phone, email};
    mockUser.push(newUser);

    return res(ctx.status(200), ctx.json({message: "회원가입 성공", user: newUser}));
  }),


]