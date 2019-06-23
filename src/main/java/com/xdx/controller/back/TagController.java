package com.xdx.controller.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdx.entity.TTag;
import com.xdx.service.TagService;
import com.xdx.util.JsonUtil;
import com.xdx.util.ParamModel;

@Controller
public class TagController {
	@Resource(name="tagService")
	private TagService tagService;
	@RequestMapping("tag/home")
	public ModelAndView tagHome(){
		ModelAndView mv=new ModelAndView("back/tag/TagList");
		return mv;
	}
	@RequestMapping("tag/add")
	public ModelAndView tagAdd(){
		ModelAndView mv=new ModelAndView("back/tag/TagAdd");
		return mv;
	}
	@RequestMapping("tag/edit")
	public ModelAndView tagEdit(int tagId){
		ModelAndView mv=new ModelAndView("back/tag/TagEdit");
		TTag tag=tagService.getTagById(tagId);
		mv.addObject("tag", tag);
		return mv;
	}
	@ResponseBody
	@RequestMapping("tag/list")
	public JSONObject tagList(ParamModel pm){
		Map<String,Object>maps=tagService.getTagMapByPm(pm);
		JSONObject jsonObject=JsonUtil.mapToJsonObject(maps);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("tag/save")
	public String tagSave(int type,TTag tag){
		Integer result=tagService.saveTag(type, tag);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("tag/hidden")
	public String tagHidden(int tagId){
		Integer result=tagService.hiddenTag(tagId);
		return result.toString();
	}
	//判定用户名是否已使用
	@ResponseBody
	@RequestMapping("tag/tagByTagName")
	public Map<String, Object> tagByTagName(String tagName) {
		TTag tag=tagService.getTagByTagName(tagName);
		Map<String, Object> maps = new HashMap<String, Object>();
		if (tag == null) {
			maps.put("valid", true);
		} else {
			maps.put("valid", false);
		}
		return maps;
	}
	@ResponseBody
	@RequestMapping("tag/setTags2Redis")
	public String setTags2Redis(){
		List<TTag>tagList=tagService.getTagList();
		Integer result=tagService.setTagList2Redis("tagList", tagList);
		return result.toString();
	}
}
