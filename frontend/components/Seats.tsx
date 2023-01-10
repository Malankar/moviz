import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
interface Seats {
  movie: string;
  seats: string[];
}
const Seats = () => {
  const params = useParams();
  const navigate = useNavigate();
  const number = Array.from({ length: 28 }, (_, index) => index + 1);
  const letters = Array.from({ length: 15 }, (_, index) =>
    String.fromCharCode(index + 65)
  );
  const [dbSeats, setDbSeats] = useState<Seats | null>(null);
  const spaces = [
    "B1",
    "C1",
    "D1",
    "E1",
    "F1",
    "G1",
    "H1",
    "I1",
    "J1",
    "K1",
    "L1",
    "M1",
    "N1",
    "O1",
    "N2",
    "N3",
    "O2",
    "O3",
    "O4",
    "B22",
    "B23",
    "C22",
    "C23",
    "D22",
    "D23",
    "E22",
    "E23",
    "F22",
    "F23",
    "G22",
    "G23",
    "H22",
    "H23",
    "I22",
    "I23",
    "J22",
    "J23",
    "K22",
    "K23",
    "L22",
    "L23",
    "M22",
    "M23",
    "N22",
    "N23",
    "O22",
    "O23",
    "G28",
    "H26",
    "H27",
    "H28",
    "I26",
    "I27",
    "I28",
    "J26",
    "J27",
    "J28",
    "K26",
    "K27",
    "K28",
    "L26",
    "L27",
    "L28",
    "M26",
    "M27",
    "M28",
    "N26",
    "N27",
    "N28",
    "O24",
    "O25",
    "O26",
    "O27",
    "O28",
  ];
  const [selectedSeats, setSelectedSeats] = useState<string[]>([]);
  const handleSeatClick = (letter: string, number: number) => {
    const element = letter + number;
    if (params.seats != undefined) {
      if (
        selectedSeats.length < parseInt(params.seats) ||
        selectedSeats.indexOf(element) !== -1
      ) {
        if (selectedSeats.indexOf(element) !== -1) {
          setSelectedSeats(selectedSeats.filter((seat) => seat !== element));
        } else {
          setSelectedSeats([...selectedSeats, element]);
        }
      }
    }
  };

  const [user, setUser] = useState(null);

  useEffect(() => {
    setUser(JSON.parse(JSON.stringify(localStorage.getItem("user"))));
  }, []);

  const handleConfirmSeats = async () => {
    if (user !== null) {
      const email = JSON.parse(user).email;
      try {
        await axios.patch("http://localhost:8080/seats", {
          seats: selectedSeats,
          movie: params.name,
          users: {
            [email]: selectedSeats,
          },
        });
        navigate(`/${params.name}`, { replace: true });
      } catch (error) {
        console.log(error);
      }
    }
  };
  async function getSeats() {
    try {
      const res = await axios.get(
        `http://localhost:8080/seats?movie=${params.name}`
      );

      setDbSeats(res.data);
    } catch (error) {
      setDbSeats(null);
    }
  }
  useEffect(() => {
    getSeats();
  }, []);

  return (
    <div className="scale-75 flex justify-center">
      <div>
        <p className="text-lg">Royal-Rs. 350.00</p>
        <div className="flex items-start mt-2">
          <div>
            {letters
              .filter((data) => data < "E")
              .map((letter) => (
                <div className="text-lg p-2 flex items-center" key={letter}>
                  <div className="mr-16">{letter}</div>
                  <div className="flex">
                    {number.map((number) => (
                      <div
                        className={`mr-2 w-8 h-8 flex items-center justify-center  cursor-pointer border-[1px] shadow-lg font-semibold hover:bg-blue-600 hover:text-white ${
                          selectedSeats.includes(letter + number) &&
                          `bg-blue-600 text-white border-none `
                        } ${
                          spaces.includes(letter + number) &&
                          `bg-white text-white border-none pointer-events-none shadow-none`
                        }
                        ${
                          dbSeats?.seats.includes(letter + number) &&
                          `bg-gray-300 text-white border-none pointer-events-none shadow-none`
                        }
                        
                        `}
                        onClick={() => handleSeatClick(letter, number)}
                        key={`${letter}${number}`}
                      >
                        {!spaces.includes(letter + number) && number}
                      </div>
                    ))}
                  </div>
                </div>
              ))}
          </div>
        </div>
        <p className="text-lg">Club-Rs. 300.00</p>
        <div className="flex items-start mt-2">
          <div>
            {letters
              .filter((data) => data > "D" && data < "M")
              .map((letter) => (
                <div className="text-lg p-2 flex items-center" key={letter}>
                  <div className="mr-16">{letter}</div>
                  <div className="flex">
                    {number.map((number) => (
                      <div
                        className={`mr-2 w-8 h-8 flex items-center justify-center  cursor-pointer border-[1px] shadow-lg font-semibold hover:bg-blue-600 hover:text-white ${
                          selectedSeats.includes(letter + number) &&
                          `bg-blue-600 text-white border-none `
                        } ${
                          spaces.includes(letter + number) &&
                          `bg-white text-white border-none pointer-events-none shadow-none`
                        }
                        ${
                          dbSeats?.seats.includes(letter + number) &&
                          `bg-gray-300 text-white border-none pointer-events-none shadow-none`
                        }
                        `}
                        onClick={() => handleSeatClick(letter, number)}
                        key={`${letter}${number}`}
                      >
                        {!spaces.includes(letter + number) && number}
                      </div>
                    ))}
                  </div>
                </div>
              ))}
          </div>
        </div>
        <p className="text-lg">Executive-Rs. 300.00</p>
        <div className="flex items-start mt-2">
          <div>
            {letters
              .filter((data) => data > "L" && data < "P")
              .map((letter) => (
                <div className="text-lg p-2 flex items-center" key={letter}>
                  <div className="mr-16">{letter}</div>
                  <div className="flex">
                    {number.map((number) => (
                      <div
                        className={`mr-2 w-8 h-8 flex items-center justify-center  cursor-pointer border-[1px] shadow-lg font-semibold hover:bg-blue-600 hover:text-white ${
                          selectedSeats.includes(letter + number) &&
                          `bg-blue-600 text-white border-none `
                        } ${
                          spaces.includes(letter + number) &&
                          `bg-white text-white border-none pointer-events-none shadow-none`
                        }
                        ${
                          dbSeats?.seats.includes(letter + number) &&
                          `bg-gray-300 text-white border-none pointer-events-none shadow-none`
                        }
                        `}
                        onClick={() => handleSeatClick(letter, number)}
                        key={`${letter}${number}`}
                      >
                        {!spaces.includes(letter + number) && number}
                      </div>
                    ))}
                  </div>
                </div>
              ))}
          </div>
        </div>
        <div className="flex justify-center mt-5">
          <button
            className="px-20 text-2xl py-5 bg-red-500 text-white rounded-lg"
            onClick={handleConfirmSeats}
          >
            Confirm Seats
          </button>
        </div>
      </div>
    </div>
  );
};
export default Seats;
