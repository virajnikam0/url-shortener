import { useState } from "react";
import { DOMAIN_NAME } from "./../utils/appConstants";
import URL_IMG from "./../assets/undraw_dark_analytics_re_2kvy.svg";
import { getShortUrl } from "./../services/apiService";
import { getLongUrl } from "./../services/apiService";

const LongToShort = () => {
	const [longUrlInput, setLongUrlInput] = useState("");
	const [shortUrlInput, setShortUrlInput] = useState("");

	const [shortUrlComplete, setShortUrlComplete] = useState({});
	const [longToShortUrlModel, setLongToShortUrlModel] = useState(null);

	const [shortToLongUrlModel, setShortToLongUrlModel] = useState(null);

	const handleOnSubmitRequestLongToShortUrl = async (event) => {
		event.preventDefault();
		try {
			//create input model
			const inputModel = {
				longUrl: longUrlInput,
			};

			// send request to service
			const result = await getShortUrl(inputModel);

			// handle result
			if (result && result.data) {
				const data = result.data;
				if (data.isSuccess && data.statusCode == 200) {
					setShortUrlComplete(`${DOMAIN_NAME}${data.shortUrl}`);
					setLongToShortUrlModel(data);
				} else {
					setLongToShortUrlModel(null);
					throw new Error(`${data.statusCode}: ${data.message}`);
				}
			}
		} catch (error) {
			alert(error);
		}
	};

	const handleOnChangeInputLongToShort = (event) => {
		setLongUrlInput(event.target.value);
	};

	const handleOnSubmitRequestShortToLongUrl = async (event) => {
		event.preventDefault();
		try {
			//create input model
			const inputModel = {
				shortUrl: shortUrlInput,
			};

			// send request to service
			const result = await getLongUrl(inputModel);

			// handle result
			if (result && result.data) {
				const data = result.data;
				if (data.isSuccess && data.statusCode == 200) {
					setShortToLongUrlModel(data);
				} else {
					setShortToLongUrlModel(null);
					throw new Error(`${data.statusCode}: ${data.message}`);
				}
			}
		} catch (error) {
			alert(error);
		}
	};

	const handleOnChangeInputShortToLong = (event) => {
		setShortUrlInput(event.target.value);
	};

	const handleCopyToClipboard = (event, input) => {
		alert(input);
	};

	return (
		<>
			<h5 className="col-12 pt-2 pb-2 fw-bold">
				Url Shortener
				<img className="header-img-size" src={URL_IMG} />
			</h5>
			<div className="col-12">
				<ul className="nav nav-tabs" id="myTab" role="tablist">
					<li className="nav-item" role="presentation">
						<button
							className="nav-link active fw-bold"
							id="short-to-long-url-tab"
							data-bs-toggle="tab"
							data-bs-target="#short-to-long-url-pane"
							type="button"
							role="tab"
							aria-controls="short-to-long-url-pane"
							aria-selected="true">
							Create Short Url
						</button>
					</li>
					<li className="nav-item" role="presentation">
						<button
							className="nav-link fw-bold"
							id="long-to-short-url-tab"
							data-bs-toggle="tab"
							data-bs-target="#long-to-short-url-pane"
							type="button"
							role="tab"
							aria-controls="long-to-short-url-pane"
							aria-selected="false">
							Get Long Url
						</button>
					</li>
				</ul>
				<div className="tab-content p-3 border-top-0 border" id="myTabContent">
					{/* <!-- long to short url convert --> */}
					<div
						className="tab-pane fade show active"
						id="short-to-long-url-pane"
						role="tabpanel"
						aria-labelledby="short-to-long-url"
						tabIndex="0">
						<div className="row">
							<div className="col-10 center-x">
								<h5>Request:</h5>
								<form onSubmit={handleOnSubmitRequestLongToShortUrl}>
									<div className="input-group m-3">
										<span className="input-group-text" id="longUrlLable">
											Enter Long Url
										</span>
										<input
											className="form-control  text-center"
											type="url"
											name="longUrlInput"
											id="longUrlInput"
											aria-label="longUrlInput"
											placeholder="https://example.com"
											onChange={handleOnChangeInputLongToShort}
											value={longUrlInput}
										/>
										<button className="btn btn-success mr-2" type="submit">
											Create Short Url
										</button>
									</div>
								</form>

								{/* Loading */}
								{/* <div className="row">
          <div className="col-12 text-center">
            Loading...
          </div>
        </div> */}
								{longToShortUrlModel && (
									<div className="row">
										<div className="col-12">
											<h5>Output:</h5>
											<ul>
												<li>
													<span className="fw-bold"> Short url created:</span>
													<a
														href={shortUrlComplete}
														className="badge bg-primary text-white badge-sm rounded-pill"
														target="_blank">
														{ shortUrlComplete }
													</a>
													<button
														className="btn btn-sm btn-dark ml-2"
														onClick={(e) =>
															handleCopyToClipboard(e, shortUrlComplete)
														}>
														Copy
													</button>
												</li>
												<li>
													<span className="fw-bold"> Created On: </span>{" "}
													{longToShortUrlModel.dateCreated}
												</li>
											</ul>
										</div>
									</div>
								)}
							</div>
						</div>
					</div>
					{/* <!-- long to short url convert --> */}
					<div
						className="tab-pane fade"
						id="long-to-short-url-pane"
						role="tabpanel"
						aria-labelledby="long-to-short-tab"
						tabIndex="0">
						<div className="row">
							<div className="col-10 center-x">
								<h5>Request:</h5>
								<form onSubmit={handleOnSubmitRequestShortToLongUrl}>
									<div className="input-group m-3">
										<span className="input-group-text" id="shortUrlLable">
											Enter Short Url
										</span>
										<span className="input-group-text" id="shortUrlLable">
											{DOMAIN_NAME}
										</span>
										<input
											className="form-control px-4 text-center"
											type="text"
											name="shortUrlInput"
											id="shortUrlInput"
											aria-label="shortUrlInput"
											placeholder="abc1234"
											onChange={handleOnChangeInputShortToLong}
											value={shortUrlInput}
										/>
										<button className="btn btn-success mr-2" type="submit">
											Get Long Url
										</button>
									</div>
								</form>

								{/* Loading */}
								{/* <div className="row">
                                <div className="col-12 text-center">
                                    Loading...
                                </div>
                                </div> */}

								{shortToLongUrlModel && (
									<div className="row">
										<div className="col-12">
											<h5>Output:</h5>
											<ul>
												<li>
													<span className="fw-bold">Long url: </span>
													<a
														href={shortToLongUrlModel.longUrl}
														className="badge bg-primary text-white badge-sm rounded-pill"
														target="_blank">
														{shortToLongUrlModel.longUrl}
													</a>
													<button
														className="btn btn-sm btn-dark"
														onClick={(e) =>
															handleCopyToClipboard(
																e,
																shortToLongUrlModel.longUrl
															)
														}>
														Copy
													</button>
												</li>
												<li>
													{" "}
													<span className="fw-bold">Visitors Count: </span>
													{shortToLongUrlModel.count}
												</li>
												<li>
													{" "}
													<span className="fw-bold">Created On: </span>
													{shortToLongUrlModel.dateCreated}
												</li>
											</ul>
										</div>
									</div>
								)}
							</div>
						</div>
					</div>
				</div>
			</div>
		</>
	);
};

export default LongToShort;
