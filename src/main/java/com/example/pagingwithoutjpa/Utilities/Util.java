package com.example.pagingwithoutjpa.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Util {

	private static Logger LOG = LoggerFactory.getLogger(Util.class);

	public static Page<?> toPage(List<?> list, Pageable pageable, boolean isNativeType, boolean isAsc) {
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), list.size());
		if (isNativeType) {
			Collections.sort(list, (x, y) -> x.toString().compareToIgnoreCase(y.toString()));
			if (!isAsc) {
				LOG.info("Order DESC");
				Collections.sort(list, Collections.reverseOrder());
			}
		} else {
			LOG.info("Order objects With Sort");
			objectsWithSort(list, pageable.getSort());
		}

		LOG.info("Valores:{}", list);

		if (start > list.size())
			return new PageImpl<>(new ArrayList<>(), pageable, list.size());
		return new PageImpl<>(list.subList(start, end), pageable, list.size());
	}

	private static List<?> objectsWithSort(List<?> list, Sort sortBy) {
		var listOrder = sortBy.toList();
		if (listOrder.size() == 1) {
			var isAsc = listOrder.get(0).getDirection().isAscending();
			String nameParameter = listOrder.get(0).getProperty();
			Collections.sort(list, (x, y) -> {
				var fieldX = org.springframework.util.ReflectionUtils.findField(x.getClass(), nameParameter);
				org.springframework.util.ReflectionUtils.makeAccessible(fieldX);

				var fieldY = org.springframework.util.ReflectionUtils.findField(y.getClass(), nameParameter);
				org.springframework.util.ReflectionUtils.makeAccessible(fieldY);
				try {
					return fieldX.get(x).toString().compareToIgnoreCase(fieldY.get(y).toString());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			});
			if (!isAsc) {
				Collections.reverse(list);
			}
		}
		return list;
	}

}
