package tw.com.eeit.shop.model.mapper;

import org.springframework.beans.BeanUtils;

import tw.com.eeit.shop.model.dto.ProductDto;
import tw.com.eeit.shop.model.entity.Product;
import tw.com.eeit.shop.utils.CommonUtil;

public class ProductMapper {

	public static ProductDto toDto(Product entity) {
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(entity, dto);

		dto.setId(entity.getProductId());
		dto.setName(entity.getProductName());

		// 將 photo 的 byte[] 轉換成 Base64 格式
		byte[] photo = entity.getProductPhoto();
		String mimeType = CommonUtil.getImageMimeType(photo);
		String base64String = CommonUtil.encodeToBase64String(photo);

		dto.setPhoto("data:%s;base64,%s".formatted(mimeType, base64String));

		return dto;
	}

}
