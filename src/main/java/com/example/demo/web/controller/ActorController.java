package com.example.demo.web.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.jdbc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.entity.Actor;
import com.example.demo.domain.entity.ActorPrefecture;
import com.example.demo.domain.entity.Prefecture;
import com.example.demo.domain.repository.ActorRepository;
import com.example.demo.domain.repository.PrefectureRepository;
import com.example.demo.web.form.ActorForm;

/**
 * ActorController is the class for actor screen control.
 */
@Controller
@RequestMapping(value = "/actor")
public class ActorController {

	final static Logger logger = LoggerFactory.getLogger(ActorController.class);

	/** {@link ActorRepository} */
	@Autowired
	ActorRepository actorRepository;

	/** {@link PrefectureRepository} */
	@Autowired
	PrefectureRepository prefectureRepository;

	/** {@link MessageSource} */
	@Autowired
	MessageSource messageSource;

	/**
	 * Requst parameter converter.
	 * <p>
	 * If request parameter is empty then convert to {@code null}.
	 *
	 * @param binder
	 *            {@link WebDataBinder}
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	/**
	 * Display actor's top screen.
	 * <p>
	 * Access /acor/ then display Actor/index.
	 *
	 * @param model
	 *            {@link Model}
	 * @return Actor/index
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("Actor + index");
		List<ActorPrefecture> list = actorRepository.selectAll();
		if (list.isEmpty()) {
			String message = messageSource.getMessage("actor.list.empty", null, Locale.JAPAN);
			model.addAttribute("emptyMessage", message);
		}
		model.addAttribute("list", list.stream().map(toForm).collect(Collectors.toList()));
		modelDump(model, "index");
		return "Actor/index";
	}

	/**
	 * Display actor's detail screen.
	 * <p>
	 * Access /acor/{id} then display Actor/detail.
	 *
	 * @param id
	 *            actor's id
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable Integer id) {
		logger.debug("Actor + detail");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Actor/detail");
		Optional<ActorPrefecture> actorOptional = actorRepository.selectById(id);
		mv.addObject("actor", toForm.apply(actorOptional.get()));
		return mv;
	}

	/**
	 * Display actor's top screen and search result.
	 * <p>
	 * Access /acor/ then display Actor/index.
	 *
	 * @param keyword
	 *            keyword for search
	 * @return {@link ModelAndView}
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam String keyword) {
		logger.debug("Actor + search");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Actor/index");
		if (StringUtils.isNotEmpty(keyword)) {
			List<ActorPrefecture> list = actorRepository.selectAllByName(keyword);
			if (list.isEmpty()) {
				String message = messageSource.getMessage("actor.list.empty", null, Locale.JAPAN);
				mv.addObject("emptyMessage", message);
			}
			mv.addObject("list", list.stream().map(toForm).collect(Collectors.toList()));
		}
		return mv;
	}

	/**
	 * Display actor's create screen.
	 * <p>
	 * Access /acor/create then display Actor/create.
	 *
	 * @param form
	 *            {@link ActorForm}
	 * @param model
	 *            {@link Model}
	 * @return Actor/create
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ActorForm form, Model model) {
		logger.debug("Actor + create");
		List<Prefecture> pref = prefectureRepository.selectAll();
		model.addAttribute("pref", pref);
		modelDump(model, "create");
		return "Actor/create";
	}

	/**
	 * Create new actor's table data and display actor's detail screen.
	 * <p>
	 * Access /acor/save then create new actor's table data and display
	 * Actor/detail.
	 *
	 * @param form
	 *            {@link ActorForm}
	 * @param result
	 *            {@link BindingResult}
	 * @param model
	 *            {@link Model}
	 * @return redirect:/actor/{id}
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute ActorForm actorForm, BindingResult result, Model model) {
		logger.debug("Actor + save");
		if (result.hasErrors()) {
			String message = messageSource.getMessage("actor.validation.error", null, Locale.JAPAN);
			model.addAttribute("errorMessage", message);
			return create(actorForm, model);
		}
		Actor actor = toEntity.apply(actorForm);
		logger.debug("actor:{}", actor.toString());
		Result<Actor> actorResult = actorRepository.insert(actor);
		modelDump(model, "save");
		return "redirect:/actor/" + actorResult.getEntity().getId().toString();
	}

	/**
	 * Delete actor's table data with id and display top screen.
	 * <p>
	 * Access /acor/delete/{id} then delete actor's table data with id and display
	 * top screen.
	 *
	 * @param id
	 *            actor's id
	 * @param attributes
	 *            {@link RedirectAttributes}
	 * @param model
	 *            {@link Model}
	 * @return redirect:
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id, RedirectAttributes attributes, Model model) {
		logger.debug("Actor + delete");
		actorRepository.delete(Actor.builder().id(id).build());
		attributes.addFlashAttribute("deleteMessage", "delete ID:" + id);
		return "redirect:/actor";
	}

	/**
	 * ActorForm to Actor entity.
	 * <p>
	 * This class is for the actor table to insert data.
	 */
	Function<ActorForm, Actor> toEntity = (ActorForm actorForm) -> {
		Short height = StringUtils.isNotEmpty(actorForm.getHeight()) ? Short.valueOf(actorForm.getHeight()) : null;
		String blood = StringUtils.isNotEmpty(actorForm.getBlood()) ? actorForm.getBlood() : null;
		LocalDate birthday = null;
		if (StringUtils.isNotEmpty(actorForm.getBirthday())) {
			DateTimeFormatter withoutZone = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime parsed = LocalDateTime.parse(actorForm.getBirthday() + " 00:00:00", withoutZone);
			birthday = parsed.toLocalDate();
		}
		Short birthplaceId = StringUtils.isNotEmpty(actorForm.getBirthplaceId())
				? Short.valueOf(actorForm.getBirthplaceId())
				: null;
		return Actor.builder().name(actorForm.getName()).height(height).blood(blood).birthday(birthday)
				.birthplaceId(birthplaceId).updateAt(LocalDateTime.now()).build();
	};

	/**
	 * ActorPrefecture convert to ActorForm.
	 * <p>
	 * This class is for the screen to using table entity data.
	 */
	Function<ActorPrefecture, ActorForm> toForm = (ActorPrefecture actorPrefecture) -> {
		return ActorForm.builder().id(actorPrefecture.getId().toString()).name(actorPrefecture.getName())
				.height(actorPrefecture.getHeight() == null ? StringUtils.EMPTY
						: actorPrefecture.getHeight().toString())
				.blood(actorPrefecture.getBlood() == null ? StringUtils.EMPTY : actorPrefecture.getBlood())
				.birthday(actorPrefecture.getBirthday() == null ? StringUtils.EMPTY
						: actorPrefecture.getBirthday().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
				.birthplaceId(actorPrefecture.getPrefecture().getId() == null ? StringUtils.EMPTY
						: actorPrefecture.getPrefecture().getId().toString())
				.birthplaceName(actorPrefecture.getPrefecture().getName() == null ? StringUtils.EMPTY
						: actorPrefecture.getPrefecture().getName())
				.updateAt(actorPrefecture.getUpdateAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
				.build();
	};

	/**
	 * For debug log.
	 * <p>
	 * This class is for watching screen model data.
	 *
	 * @param model
	 *            {@link Model}
	 * @param m
	 *            thymeleaf template
	 */
	private void modelDump(Model model, String m) {
		logger.debug(" ");
		logger.debug("Model:{}", m);
		Map<String, Object> mm = model.asMap();
		for (Entry<String, Object> entry : mm.entrySet()) {
			logger.debug("key:{}", entry.getKey());
		}
	}
}
