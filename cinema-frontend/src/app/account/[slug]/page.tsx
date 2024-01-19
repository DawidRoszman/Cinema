"use client";
import React, { useContext, useEffect, useState } from "react";
import { TicketsContext } from "../context";
import { formatDateForView } from "@/utils/formatDateForView";
import axios from "axios";
import { useToken } from "@/app/components/TokenContext";
import { api } from "@/utils/apiAddress";
import { Seat } from "@/types/types";
import Loading from "@/components/Loading";

const TicketDetails = ({ params }: { params: { slug: string } }) => {
  const token = useToken();
  if (token === null) return <div>Unauthorized</div>;
  const tickets = useContext(TicketsContext);
  if (tickets === null) return <div>Loading...</div>;
  const ticket = tickets.find((ticket) => ticket.id === params.slug);
  if (ticket === undefined) return <div>Loading...</div>;
  const [seats, setSeats] = useState<Seat[] | null>(null);
  useEffect(() => {
    const fetchSeats = async () => {
      const response = await axios.get(
        api + "/api/v1/tickets/get/" + ticket.id,
        {
          headers: {
            Authorization: "Bearer " + token.token,
          },
        },
      );
      setSeats(response.data);
    };
    fetchSeats();
  }, []);

  if (seats === null) return <Loading />;
  return (
    <div className="pt-12 grid place-items-center">
      <div className="card max-w-md bg-base-100 shadow-xl">
        <div className="card-body">
          <h2 className="card-title">{ticket.movieTitle}</h2>
          <h2>{formatDateForView(new Date(ticket.screeningDate))}</h2>
          <p>Auditorium {ticket.auditoriumNumber}</p>
          <p>Your seat/s:</p>
          {seats
            .toSorted((a, b) => (a.row > b.row ? 1 : -1))
            .map((seat) => {
              return (
                <p key={seat.id}>
                  Row: {seat.row} Place: {seat.column}{" "}
                </p>
              );
            })}
          <p>
            Your ticket number:
            <br /> {ticket.id}
          </p>
          <p className="grid place-items-center">
            <img
              src={
                "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" +
                ticket.id
              }
            />
          </p>
        </div>
      </div>
    </div>
  );
};

export default TicketDetails;
