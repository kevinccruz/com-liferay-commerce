/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.shipping.engine.fixed.service.impl;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.service.CommerceShippingMethodService;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionRel;
import com.liferay.commerce.shipping.engine.fixed.service.base.CommerceShippingFixedOptionRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.math.BigDecimal;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShippingFixedOptionRelServiceImpl
	extends CommerceShippingFixedOptionRelServiceBaseImpl {

	@Override
	public CommerceShippingFixedOptionRel addCommerceShippingFixedOptionRel(
			long commerceShippingMethodId, long commerceShippingFixedOptionId,
			long commerceInventoryWarehouseId, long commerceCountryId,
			long commerceRegionId, String zip, double weightFrom,
			double weightTo, BigDecimal fixedPrice,
			BigDecimal rateUnitWeightPrice, double ratePercentage,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPPING_METHODS);

		return commerceShippingFixedOptionRelLocalService.
			addCommerceShippingFixedOptionRel(
				commerceShippingMethodId, commerceShippingFixedOptionId,
				commerceInventoryWarehouseId, commerceCountryId,
				commerceRegionId, zip, weightFrom, weightTo, fixedPrice,
				rateUnitWeightPrice, ratePercentage, serviceContext);
	}

	@Override
	public void deleteCommerceShippingFixedOptionRel(
			long commerceShippingFixedOptionRelId)
		throws PortalException {

		CommerceShippingFixedOptionRel commerceShippingFixedOptionRel =
			commerceShippingFixedOptionRelLocalService.
				getCommerceShippingFixedOptionRel(
					commerceShippingFixedOptionRelId);

		_portletResourcePermission.check(
			getPermissionChecker(), commerceShippingFixedOptionRel.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPPING_METHODS);

		commerceShippingFixedOptionRelLocalService.
			deleteCommerceShippingFixedOptionRel(
				commerceShippingFixedOptionRel);
	}

	@Override
	public CommerceShippingFixedOptionRel fetchCommerceShippingFixedOptionRel(
			long commerceShippingFixedOptionRelId)
		throws PortalException {

		CommerceShippingFixedOptionRel commerceShippingFixedOptionRel =
			commerceShippingFixedOptionRelLocalService.
				fetchCommerceShippingFixedOptionRel(
					commerceShippingFixedOptionRelId);

		if (commerceShippingFixedOptionRel != null) {
			_portletResourcePermission.check(
				getPermissionChecker(),
				commerceShippingFixedOptionRel.getGroupId(),
				CommerceActionKeys.MANAGE_COMMERCE_SHIPPING_METHODS);
		}

		return commerceShippingFixedOptionRel;
	}

	@Override
	public List<CommerceShippingFixedOptionRel>
			getCommerceShippingMethodFixedOptionRels(
				long commerceShippingMethodId, int start, int end,
				OrderByComparator<CommerceShippingFixedOptionRel>
					orderByComparator)
		throws PortalException {

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodService.getCommerceShippingMethod(
				commerceShippingMethodId);

		return commerceShippingFixedOptionRelLocalService.
			getCommerceShippingMethodFixedOptionRels(
				commerceShippingMethod.getCommerceShippingMethodId(), start,
				end, orderByComparator);
	}

	@Override
	public int getCommerceShippingMethodFixedOptionRelsCount(
			long commerceShippingMethodId)
		throws PortalException {

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodService.getCommerceShippingMethod(
				commerceShippingMethodId);

		return commerceShippingFixedOptionRelLocalService.
			getCommerceShippingMethodFixedOptionRelsCount(
				commerceShippingMethod.getCommerceShippingMethodId());
	}

	@Override
	public CommerceShippingFixedOptionRel updateCommerceShippingFixedOptionRel(
			long commerceShippingFixedOptionRelId,
			long commerceInventoryWarehouseId, long commerceCountryId,
			long commerceRegionId, String zip, double weightFrom,
			double weightTo, BigDecimal fixedPrice,
			BigDecimal rateUnitWeightPrice, double ratePercentage)
		throws PortalException {

		CommerceShippingFixedOptionRel commerceShippingFixedOptionRel =
			commerceShippingFixedOptionRelLocalService.
				getCommerceShippingFixedOptionRel(
					commerceShippingFixedOptionRelId);

		_portletResourcePermission.check(
			getPermissionChecker(), commerceShippingFixedOptionRel.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPPING_METHODS);

		return commerceShippingFixedOptionRelLocalService.
			updateCommerceShippingFixedOptionRel(
				commerceShippingFixedOptionRelId, commerceInventoryWarehouseId,
				commerceCountryId, commerceRegionId, zip, weightFrom, weightTo,
				fixedPrice, rateUnitWeightPrice, ratePercentage);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommerceShippingFixedOptionRelServiceImpl.class,
				"_portletResourcePermission", CommerceConstants.RESOURCE_NAME);

	@ServiceReference(type = CommerceShippingMethodService.class)
	private CommerceShippingMethodService _commerceShippingMethodService;

}