package com.hemant.graphql.model.pagination;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

/**
 * 
 * PageRequest object encapsulating all the pagination attributes
 * to use pageRequest as requestParam -
 * https://stackoverflow.com/questions/16942193/spring-mvc-complex-object-as-get-requestparam
 * 
 * @author hemant
 *
 */

public class PageRequest {

	private static final Logger LOG = LoggerFactory.getLogger(PageRequest.class);
	private static final String NEW_LINE = "\n";
	// starts with 0
	private Integer pageNumber;
	// minimum size =1
	private Integer pageSize;
	private SortOrder sortOrder;
	private String sortBy;


	public PageRequest(int pageNumber, int pageSize, SortOrder sortOrder, String sortBy) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.sortOrder = sortOrder;
		this.sortBy = sortBy;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setSortOrder(String sortOrderString) {
		this.sortOrder = SortOrder.valueOf(sortOrderString);
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	@Override
	public String toString() {
		return "PageRequest [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", sortOrder=" + sortOrder
				+ ", sortBy=" + sortBy + "]";
	}

	/**
	 * Performs validations on the request
	 * @param req
	 * @return
	 */
	public static String validatePaginationParams(PageRequest req) {
		List<String> validationErrors = new ArrayList<>();
		if (null == req.pageNumber || req.pageNumber < 0) {
			LOG.error("Minimum PageNumber =0. PageNumber :{} must be greater than 0", req.pageNumber);
			validationErrors.add("PageNumber must be greater than or equal to 0!");
		}

		if (null == req.pageSize || req.pageSize < 1) {
			LOG.error("PageSize :{} must be greater than 0", req.pageSize);
			validationErrors.add("PageSize must be greater than 0");
		}

		if (null == req.sortOrder) {
			LOG.error("SortOrder :{} must be specified", req.sortOrder);
			validationErrors.add("SortOrder must be specified");
		}

		if (StringUtils.isBlank(req.sortBy)) {
			LOG.error("SortBy :{} must be specified", req.sortBy);
			validationErrors.add("SortBy must be specified");
		}
		return getConsolidatedError(validationErrors);
	}

	/**
	 * Consolidates all the errors
	 * @param validationErrors
	 * @return
	 */
	private static String getConsolidatedError(List<String> validationErrors) {
		if (CollectionUtils.isEmpty(validationErrors)) {
			return null;
		}
		return StringUtils.join(validationErrors, NEW_LINE);
	}

	/**
	 * Gets the pagination query for mongo
	 * 
	 * @param req
	 * @return
	 */
	public static Query getPaginationQueryForMongo(PageRequest req) {
		final int limit = req.pageSize;
		final int offset = req.pageNumber * req.pageSize;
		Query query = new Query();

		query.skip(offset).limit(limit).with(new Sort(Sort.Direction.fromString(req.sortOrder.toString()), req.sortBy));
		LOG.info("The pagination query is limit:{}, offset:{}, sort:{}", query.getLimit(), query.getSkip(),
				query.getSortObject());
		return query;
	}
}
