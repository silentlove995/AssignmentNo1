package clients.fund;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author datdv
 */
@FeignClient(
        name = "fund",
        url = "${clients.fund.url}"
)
public interface FundClient {

    @GetMapping("/fund")
    FundResponse getFundResponse();
}
