import { Disclosure } from "@headlessui/react";
import { useState } from "react";
import { BsChevronDown } from "react-icons/bs";
function Accordian() {
  const [languages, setLanguages] = useState<{ [key: string]: boolean }>({});
  const [genres, setGenres] = useState<{ [key: string]: boolean }>({});
  const handleClick = (language: string) => {
    if (languages[language]) {
      // Language is already in the list, remove it
      const newLanguages = { ...languages };
      delete newLanguages[language];
      setLanguages(newLanguages);
    } else {
      // Language is not in the list, add it
      setLanguages({ ...languages, [language]: true });
    }
  };
  const clearLanguages = (
    e: React.MouseEvent<HTMLParagraphElement, MouseEvent>
  ) => {
    e.stopPropagation();
    setLanguages({});
  };
  const languageList = [
    "English",
    "Telugu",
    "Gujrati",
    "Hindi",
    "Tamil",
    "Haryanavi",
    "Marathi",
    "Malayalam",
    "Kannada",
  ];

  const handleClickGenre = (genre: string) => {
    if (genres[genre]) {
      // Genre is already in the list, so remove it
      const newGenres = { ...genres };
      delete newGenres[genre];
      setGenres(newGenres);
    } else {
      // Genre is not in the list, so add it
      setGenres({ ...genres, [genre]: true });
    }
  };

  const clearGenres = (
    e: React.MouseEvent<HTMLParagraphElement, MouseEvent>
  ) => {
    e.stopPropagation();
    setGenres({});
  };

  const genreList = [
    "Drama",
    "Comedy",
    "Action",
    "Thriller",
    "Adventure",
    "Romantic",
    "Family",
    "Animation",
    "Biography",
    "Classic",
    "Crime",
    "Fantasy",
    "Horror",
    "Musical",
    "Mystery",
    "Sci-Fi",
    "Sport",
    "War",
    "Western",
  ];

  return (
    <>
      <div className="w-full mt-5">
        <div className="mx-auto w-full max-w-md rounded-2xl bg-white p-2">
          <div className="bg-white shadow-md rounded-lg mb-5">
            <Disclosure defaultOpen>
              {({ open }) => (
                <>
                  <Disclosure.Button className="flex w-full items-center justify-between rounded-lg bg-white px-4 py-2 text-left text-sm font-medium text-purple-900 hover:bg-gray-200 focus:outline-none focus-visible:ring focus-visible:ring-purple-500 focus-visible:ring-opacity-75">
                    <div className="flex items-center">
                      <BsChevronDown
                        className={`${
                          open ? "rotate-180 transform mr-3" : "mr-3"
                        } h-5 w-5 text-purple-500`}
                      />
                      <span className="text-lg">Languages</span>
                    </div>
                    <p
                      className="text-gray-400 hover:text-black"
                      onClick={(e) => clearLanguages(e)}
                    >
                      Clear
                    </p>
                  </Disclosure.Button>
                  <Disclosure.Panel className="px-4 pt-4 pb-2 text-sm text-gray-500">
                    <div className="grid grid-rows-3 grid-flow-col gap-3">
                      {languageList.map((language) => (
                        <span
                          key={language}
                          className={`bg-blue-0 border-[1px] border-gray-400 text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer ${
                            languages[language]
                              ? "bg-blue-500 text-white"
                              : "text-black"
                          }`}
                          onClick={() => handleClick(language)}
                        >
                          {language}
                        </span>
                      ))}
                    </div>
                  </Disclosure.Panel>
                </>
              )}
            </Disclosure>
          </div>
          <div className="bg-white shadow-md rounded-lg mb-5">
            <Disclosure>
              {({ open }) => (
                <>
                  <Disclosure.Button className="flex w-full items-center justify-between rounded-lg bg-white px-4 py-2 text-left text-sm font-medium text-purple-900 hover:bg-gray-200 focus:outline-none focus-visible:ring focus-visible:ring-purple-500 focus-visible:ring-opacity-75">
                    <div className="flex items-center">
                      <BsChevronDown
                        className={`${
                          open ? "rotate-180 transform mr-3" : "mr-3"
                        } h-5 w-5 text-purple-500`}
                      />
                      <span className="text-lg">Genres</span>
                    </div>
                    <p
                      className="text-gray-400 hover:text-black"
                      onClick={(e) => clearGenres(e)}
                    >
                      Clear
                    </p>
                  </Disclosure.Button>
                  <Disclosure.Panel className="px-4 pt-4 pb-2 text-sm text-gray-500">
                    <div className="grid gap-2 overflow-x-hidden overflow-y-auto h-96">
                      {genreList.map((genre) => (
                        <span
                          key={genre}
                          className={`bg-blue-0 border-[1px] border-gray-400 text-xs font-semibold mr-2 px-2.5 py-3 rounded hover:bg-blue-600 hover:text-white cursor-pointer ${
                            genres[genre]
                              ? "bg-blue-500 text-white"
                              : "text-black"
                          }`}
                          onClick={() => handleClickGenre(genre)}
                        >
                          {genre}
                        </span>
                      ))}
                    </div>
                  </Disclosure.Panel>
                </>
              )}
            </Disclosure>
          </div>
        </div>
      </div>
      <div className="px-3">
        <button className="bg-blue-500 px-6 py-2 text-white font-bold rounded-lg">
          Submit
        </button>
      </div>
    </>
  );
}

export default Accordian;
