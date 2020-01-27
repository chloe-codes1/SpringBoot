package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.bean.FileVO;
import com.example.demo.bean.HealthBoard;
import com.example.demo.bean.UserVO;
import com.example.demo.service.BoardMapper;
import com.example.demo.service.UserMapper;
import com.example.demo.utils.WebUtils;

@Controller
public class BoardController {

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private UserMapper userMapper;

	// main 창 이동
	@RequestMapping("/main")
	public void main(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		model.addAttribute("login", session.getAttribute("login"));

	}

	// login 창 이동
	@RequestMapping("/login2")
	public void login(@ModelAttribute("user") UserVO user) {

	}
	
	// login ver2)

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginPage(Model model) {
 
    }
	
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
    }
	
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
    
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
 
        return "userInfoPage";
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
    
    
    

	// logout.do => 세션 끊기
	@RequestMapping("/logout.do")
	public String doLogout(@ModelAttribute("user") UserVO user, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();

		if (session != null) {
			session.invalidate();
		}
		return "main";
	}

	// login.do
	@RequestMapping("/login.do")
	public String doLogin(@ModelAttribute("user") UserVO user, HttpServletRequest request, Model model)
			throws Exception {

		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String role = request.getParameter("role");

		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setRole(role);

		UserVO result = null;

		result = userMapper.login(user);

		if (id == null || password == null || id.length() == 0) {

			model.addAttribute("msg", "비밀번호를 다시 입력하세요");
			model.addAttribute("id", id);

			return "login";
		}

		if (result != null) {

			session.setAttribute("login", result.getName());
			session.setAttribute("id", result.getId());
			session.setAttribute("role", result.getRole());

		} else {

			model.addAttribute("msg", "로그인 실패");
			model.addAttribute("id", id);

			return "login";
		}

		return "redirect:main";
	}

	// bmi check
	@RequestMapping("/bmi")
	public String bmi(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		model.addAttribute("login", session.getAttribute("login"));

		return "bmi";
	}

	// paging final !!!!
	@RequestMapping(value = { "boardList/{pagenum}" })
	public String boardList(@PathVariable int pagenum, @ModelAttribute("healthBoard") HealthBoard healthBoard,
			Model model, HttpServletRequest request, Pageable pageable) {

		List<HealthBoard> list = boardMapper.getBoardList();
		List<HealthBoard> onePage = new ArrayList<HealthBoard>();

		int listSize = list.size() / 10 + 1;

		if (pagenum == 0) {
			pagenum = 1;
		} else {
			pagenum = pagenum * 10 - 10;
		}
		System.out.println(pagenum);

		for (int i = pagenum; i < pagenum + 10; i++) {

			if (i == list.size()) {
				break;
			}
			onePage.add(list.get(i));
		}

		HttpSession session = request.getSession();
		model.addAttribute("login", session.getAttribute("login"));
		
		model.addAttribute("count", boardMapper.totalCnt());
		model.addAttribute("boardList", onePage);
		model.addAttribute("listSize", listSize);

		return "boardList";
	}

	// 상세페이지 Data 보여주기
	@GetMapping("/boardData/{postNo}")
	public String boardData(HttpServletRequest request, @PathVariable("postNo") int postNo,
			@ModelAttribute("healthBoard") HealthBoard healthBoard, Model model) {
		HttpSession session = request.getSession();

		boardMapper.viewCount(postNo);

		//files (images) 출력
		if (boardMapper.fileView(postNo) != null) {

			model.addAttribute("postNo", postNo);
			model.addAttribute("boardData", boardMapper.boardView(postNo));
			model.addAttribute("login", session.getAttribute("login"));
			  
			List<String> fileList = new ArrayList<String>();
			List<FileVO> files  = boardMapper.fileView(postNo);
			
			
			  Iterator<FileVO> iterator = files.iterator();
			  
		        while (iterator.hasNext()) {
		           
		            String fileName = (String) iterator.next().getFileName(); 
		            System.out.println(fileName);
		            fileList.add(fileUrl + fileName);
		        }
		       
		    System.out.println(fileList);
			model.addAttribute("fileData", fileList);
			model.addAttribute("files", files);

		} else {
			model.addAttribute("login", session.getAttribute("login"));
			model.addAttribute("postNo", postNo);
			model.addAttribute("boardData", boardMapper.boardView(postNo));
		}
		return "boardData";
	}
	
	//file download
	@RequestMapping("/fileDown/{fno}")
	public ResponseEntity<InputStreamResource> fileDown (@PathVariable int fno,
						  HttpServletRequest request,
						  @ModelAttribute("healthBoard") HealthBoard healthBoard) throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		FileVO fileVO = boardMapper.fileDownView(fno);
		System.out.println("fileVO => " + fileVO);
		
		String fileUrl = fileVO.getFileUrl();
		String fileName = fileVO.getFileName();
		
		String oriFileName = fileVO.getFileOriName();
		
		System.out.println("fileUrl =>" + fileUrl);
		System.out.println("fileName => " +fileName);
		System.out.println("oriFilename => " + oriFileName);
		
		File file = new File(request.getServletContext().getRealPath(fileUrl) +fileName);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + 
						java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+ ", "\\ ") + "\"")
						//new String(oriFileName.getBytes("UTF-8"),"ISO8859_1"))
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentLength(file.length())
                .body(resource);
	}
	
	// Search
	// ( by writer or title ) => list에서 검색
	@GetMapping("/search")
	public String search(@ModelAttribute("healthBoard") HealthBoard healthBoard,
			@RequestParam(value = "condition", required = true) String condition,
			@RequestParam(value = "keyword", required = true) String keyword, Model model) {

		List<HealthBoard> list = boardMapper.searchView2(condition, keyword);

		System.out.println(condition);
		System.out.println(keyword);

		System.out.println(list);

		model.addAttribute("boardList", list);

		return "boardList";
	}
	 
	
	// 글 쓰기 창 이동
	@GetMapping("/boardInsert")
	public void boardInsert(@ModelAttribute("healthBoard") HealthBoard healthBoard, Model model) {
	        
	}

	// 글쓰기 (이미지 업로드 1개만 )
//	@PostMapping("/insert")
//	public String insert(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception{
//		
//		FileVO file = new FileVO();
//		HealthBoard board = new HealthBoard();
//
//    	HttpSession session = request.getSession();
//		
//		if (session != null) {
//			
//			System.out.println("session.getattribute => " + session.getAttribute("login"));
//			System.out.println("session.get attribute => " +session.getAttribute("id"));
//		}
//		
//		
//		long size = files.getSize();
//		System.out.println("file size => "+ size);
//		
//		board.setWriter((String) session.getAttribute("id"));
//		board.setTitle(request.getParameter("title"));
//		board.setContent(request.getParameter("content"));
//		
//		if(files.isEmpty()) boardMapper.insertBoard(board);
//		
//		
//		String fileName = files.getOriginalFilename();
//		String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
//		File destinationFile;
//		String destinationFileName;
//		String fileUrl = "/uploadFiles/";
//				
//		do {
//			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
//			destinationFile = new File(request.getServletContext().getRealPath(fileUrl) + destinationFileName);
//			
//			System.out.println(destinationFile);
//			
//		} while (destinationFile.exists());
//		
//		destinationFile.getParentFile().mkdirs();
//		files.transferTo(destinationFile);
//		
//				
//		boardMapper.insertBoard(board);
//		
//		
//		file.setPostNo(board.getPostNo());
//		file.setFileName(destinationFileName);
//		file.setFileOriName(fileName);
//		file.setFileUrl(fileUrl);
//	
//		boardMapper.insertFile(file);
//		
//		
//		return "redirect:boardList/1";
//	}

	
	//  MultipartHttpServletRequest multi  
	// List<MultipartFile> multi
	
	
	
	// Multifile Upload 백만 스물 한번째 도전...성공..엉엉...
	public static final String fileUrl = "/uploadFiles/";
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, MultipartHttpServletRequest multi ) throws Exception {
		
		HealthBoard board = new HealthBoard();

		// Session
		HttpSession session = request.getSession();
		if (session != null) {

			System.out.println("session.getattribute => " + session.getAttribute("login"));
			System.out.println("session.get attribute => " + session.getAttribute("id"));
		}
		
		board.setWriter((String) session.getAttribute("id"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		
		boardMapper.insertBoard(board);
		
		List<MultipartFile> fileList = multi.getFiles("files");
		
		for(MultipartFile mf : fileList) {
			String fileName = mf.getOriginalFilename();
			long size = mf.getSize();
			System.out.println("file size => " + size);
			
			if (size != 0) {
			
			String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
			File destinationFile;
			String destinationFileName;

			do {
				destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
				destinationFile = new File(request.getServletContext().getRealPath(fileUrl) + destinationFileName);

				System.out.println(destinationFile);

			} while (destinationFile.exists());

			
			destinationFile.getParentFile().mkdirs();
			mf.transferTo(destinationFile);
			
			FileVO file = new FileVO();
		
			file.setPostNo(board.getPostNo());
			file.setFileName(destinationFileName);
			file.setFileOriName(fileName);
			file.setFileUrl(fileUrl);
			
			boardMapper.insertFile(file);
			}
		 }
		return "redirect:boardList/1";
	}
	
	
	// 글 delete

	@GetMapping("/delete")
	public String delete(int postNo, int groupNo, int groupOrder,
			@ModelAttribute("healthBoard") HealthBoard healthBoard) {

		if (boardMapper.replyCheck(groupNo, groupOrder) != null) {
			System.out.println("답변 있다!!");
			boardMapper.replyRemain(postNo);
		} else {
			System.out.println("답변 없다!");
			boardMapper.deleteBoard(postNo);
		}

		return "redirect:/boardList/1";
	}

	// boardUpdate 창 이동
	@GetMapping("boardUpdate/{postNo}")
	public String boardUpdate(@PathVariable("postNo") int postNo,
			@ModelAttribute("healthBoard") HealthBoard healthBoard, Model model) {

		model.addAttribute("boardData", boardMapper.boardView(postNo));

		return "boardUpdate";
	}

	// 글 Update
	@GetMapping("update/{postNo}")
	public String update(@PathVariable("postNo") int postNo, @ModelAttribute("healthBoard") HealthBoard healthBoard,
			Model model) {

		boardMapper.updateBoard(healthBoard);
		model.addAttribute("boardData", boardMapper.boardView(postNo));

		return "redirect:/boardData/{postNo}";
	}

	// 답글 쓰기 창 이동
	@GetMapping("reply/{groupNo}")
	public String reply(@PathVariable("groupNo") int groupNo, @ModelAttribute("healthBoard") HealthBoard healthBoard,
			Model model) {

		model.addAttribute("groupNo", groupNo);

		return "replyInsert";
	}

	// 답글 insert
	@GetMapping("reply/replyInsert")
	public String replyInsert(@ModelAttribute("healthBoard") HealthBoard healthBoard, Model model) {

		boardMapper.replyUpdate(healthBoard);
		boardMapper.insertReply(healthBoard);

		return "redirect:/boardList/1";
	}

}
